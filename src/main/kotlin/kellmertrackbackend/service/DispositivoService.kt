package kellmertrackbackend.service


import kellmertrackbackend.exception.InvalidRegistryException
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput
import io.github.bonigarcia.wdm.WebDriverManager
import io.github.bonigarcia.wdm.managers.ChromeDriverManager
import kellmertrackbackend.model.dto.AlvaraConstrucaoDTO
import kellmertrackbackend.model.dto.DispositivoDTO
import kellmertrackbackend.model.dto.DispositivoFormDTO
import kellmertrackbackend.model.dto.DispositivoListDTO
import kellmertrackbackend.model.entities.DispositivoEntity
import kellmertrackbackend.model.entities.mapper.DispositivoMapper
import kellmertrackbackend.model.mapper.AlvaraConstrucaoMapper
import kellmertrackbackend.repository.AlvaraConstrucaoRepository
import kellmertrackbackend.repository.DispositivoRepository
import net.sourceforge.tess4j.ITesseract
import net.sourceforge.tess4j.Tesseract
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.openqa.selenium.By
import org.openqa.selenium.OutputType
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeDriverService
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.io.FileHandler
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.io.*
import java.util.concurrent.TimeUnit


@Service
class DispositivoService(
    private val dispositivoRepository: DispositivoRepository,
    private val dispositivoMapper: DispositivoMapper,
    private val alvaraRepository : AlvaraConstrucaoRepository,
    private val alvaraMapper : AlvaraConstrucaoMapper
) {
    //aplicativo
    fun findDispositivoByNumeroInterno(numeroInterno: String) : DispositivoDTO?{
        val dispositivoEntity = dispositivoRepository.findByNumeroInterno(numeroInterno)
        return dispositivoMapper.toDispositivoDTO(dispositivoEntity)
    }

    fun atualizaDataVinculo(numeroInterno: String){
        dispositivoRepository.atualizaDataVinculo(numeroInterno)
    }

    //front end
    fun pesquisaDispositivo(numeroInterno: String, motorista: Int, veiculo: String, mac: String): List<DispositivoListDTO> {
        return dispositivoRepository.pesquisaDispositivos(numeroInterno, motorista, veiculo, mac)
    }

    fun findByNumeroInternoFormDTO(numeroInterno: String): DispositivoFormDTO? {
        return dispositivoMapper.toDispositivoFormDTO(dispositivoRepository.findByNumeroInterno(numeroInterno))
    }

    fun salvaDispositivo(dispositivo: DispositivoFormDTO): DispositivoEntity {
        val dispositivoEntity = dispositivoMapper.toDispositivoEntity(dispositivo)
        try {
            if (dispositivoRepository.findByNumeroInterno(dispositivo.numeroInterno) != null)
                throw InvalidRegistryException("Já existe um dispositivo com esse numero interno cadastrado! Verifique!")
            if (dispositivoRepository.findByMac(dispositivo.mac) != null)
                throw InvalidRegistryException("Já existe um um dispositivo com esse mac cadastrado! Verifique!")
            if (dispositivoRepository.findByVeiculoIdentificacao(dispositivo.veiculo) != null)
                throw InvalidRegistryException("Já existe um dispositivo vinculado a esse veículo cadastrado! Verifique!")
            if (dispositivoRepository.findByMotoristaId(dispositivo.motoristaId) != null)
                throw InvalidRegistryException("Já existe um dispositivo vinculado a esse motorista cadastrado! Verifique!")
            return dispositivoRepository.save(dispositivoEntity)
        } catch (e: Exception) {
            throw Exception("Ocorreu um erro ao salvar o dispositivo! ${e.message}")
        }
    }

    fun deleteDispositivo(id : Int){
        try {
            return dispositivoRepository.deleteById(id)
        }catch (e : Exception){
            throw Exception("Erro ao excluir dispositivo: ${e.message}")
        }
    }

    fun findByNumeroInterno(numeroInterno: String?): DispositivoEntity? {
        return dispositivoRepository.findByNumeroInterno(numeroInterno)
    }

    fun findByVeiculo(veiculo: String?): DispositivoEntity? {
        return dispositivoRepository.findByVeiculoIdentificacao(veiculo)
    }

    fun findByMac(mac: String?): DispositivoEntity? {
        return dispositivoRepository.findByMac(mac)
    }

    fun findByMotorista(motorista: Int): DispositivoEntity?{
        return dispositivoRepository.findByMotoristaId(motorista)
    }

    fun limpaDataVinculo(numeroInterno: String){
        dispositivoRepository.limpaDataVinculo(numeroInterno)
    }

    fun captcha(){
        //WebDriverManager.chromedriver().setup()

        val service = ChromeDriverService.createDefaultService()
        val options = ChromeOptions()

        val driver = ChromeDriver(service, options)

        //driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)

        driver.get("https://captcha.com/demos/features/captcha-demo.aspx")

        val imageElement = driver.findElement(By.xpath("//*[@id='demoCaptcha_CaptchaImage']"))

        val src = imageElement.getScreenshotAs(OutputType.FILE)

        val path = "C:\\Users\\TheBabaYaga\\Documents\\kellmertrackAPI\\src\\main\\kotlin\\kellmertrackbackend\\capchaImages\\captcha.png"

        FileHandler.copy(src, File(path))

        Thread.sleep(2000)

        val image: ITesseract = Tesseract()

        image.setDatapath("C:\\Users\\TheBabaYaga\\Desktop\\Tesseract\\tesseract-ocr-tesseract-e3f272b\\tessdata")

        val str = image.doOCR(File(path))

        println("image OCR done")
        println(str)

        Thread.sleep(10000)

        driver.findElement(By.id("captchaCode")).sendKeys(str)
    }


    /*fun baixaAlvaraConstrucao() {
        val webClient = WebClient()
        webClient.options.isJavaScriptEnabled = false
        webClient.options.isCssEnabled = false

        try {
            val page1: HtmlPage = webClient.getPage("http://www5.curitiba.pr.gov.br/gtm/pmat_alvaraconstrucao/Default.aspx")
            println("==== page1: $page1")
            val form = page1.getFormByName("Form1")
            val button: HtmlSubmitInput = form.getInputByName("btnGerarRelatorio")
            val page2 = button.click<Page>()
            //resposta obtida como retorno da requisição
            val inputStream = page2.webResponse.contentAsStream
            println("==== inputStream: $inputStream")

            val alvaraList = extrairDados(inputStream)
            /*alvaraList.forEach {
                println(it)
            }*/
            salvaAlvara(alvaraList)
            webClient.close()
        } catch (e: Exception) {
            println("Erro: ${e.message}")
        }
    }

    fun extrairDados(inputStream: InputStream): List<AlvaraConstrucaoDTO> {
        try{
            val alvaraList = mutableListOf<AlvaraConstrucaoDTO>()
            val doc = Jsoup.parse(inputStream, "UTF-8", "")
            val table = doc.select("table").first()

            table?.let {
                val rows = it.select("tr")
                println("===== rows.size: ${rows.size}")
                for (row in rows) {
                    val cols = row.select("td")
                    if (cols.size > 0) {
                        val dto = criarDTO(cols)
                        alvaraList.add(dto)
                    }
                }
            }
            return alvaraList
        }catch (e : Exception){
            throw Exception("Erro ao extrair dados: ${e.printStackTrace()}")
        }
    }

    fun salvaAlvara(alvaraList: List<AlvaraConstrucaoDTO>) {
        if (alvaraList.isNotEmpty()) {
            alvaraList.removeFirst()
            alvaraRepository.deleteAll()
            alvaraList.forEach {
                alvaraRepository.save(alvaraMapper.toAlvaraConstrucaoEntity(it))
            }
        }
    }

    fun criarDTO(cols: List<Element>): AlvaraConstrucaoDTO {
        return AlvaraConstrucaoDTO(
            cols[0].text(),
            cols[1].text(),
            cols[2].text(),
            cols[3].text(),
            cols[4].text(),
            cols[5].text(),
            cols[6].text(),
            cols[7].text(),
            cols[8].text(),
            cols[9].text(),
            cols[10].text(),
            cols[11].text(),
            cols[12].text(),
            cols[13].text(),
            cols[14].text(),
            cols[15].text(),
            cols[16].text(),
            cols[17].text(),
            cols[18].text(),
            cols[19].text(),
            cols[20].text(),
            cols[21].text(),
            cols[22].text(),
            cols[23].text(),
            cols[24].text(),
            cols[25].text(),
            cols[26].text(),
            cols[27].text(),
            cols[28].text(),
            cols[29].text(),
            cols[30].text(),
            cols[31].text(),
            cols[32].text(),
            cols[33].text()
        )
    }

    fun converte(){
        try {
            // Le o arquivo HTML
            val input = File("C:\\Users\\TheBabaYaga\\Downloads\\relatorioMensalAlvara")
            val doc = Jsoup.parse(input, "UTF-8")

            // Encontra a tabela no HTML
            val table = doc.select("table").first()

            // Cria um novo workbook do Excel
            val workbook: Workbook = HSSFWorkbook()
            val sheet: Sheet = workbook.createSheet("Dados")

            // Extrai dados da tabela HTML e insere no workbook do Excel
            var rowNum = 0
            val rows = table!!.select("tr")
            for (row in rows) {
                val excelRow: Row = sheet.createRow(rowNum++)
                val cols = row.select("td")
                var colNum = 0
                for (col in cols) {
                    excelRow.createCell(colNum++).setCellValue(col.text())
                }
            }

            val outputStream = ByteArrayOutputStream()
            workbook.write(outputStream)
            val bytes: ByteArray = outputStream.toByteArray()

            // Cria um InputStream a partir do array de bytes
            val inputStream = ByteArrayInputStream(bytes)

            // Salva o InputStream como arquivo XLS
            val fileOut = FileOutputStream(filePath)
            inputStream.copyTo(fileOut)
            workbook.write(fileOut)
            fileOut.close()

            println("Arquivo XLS gerado com sucesso!")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    val dataFormatter = DataFormatter()
    val filePath = "C:\\Users\\TheBabaYaga\\Downloads\\relatorioMensalAlvara.xls"

    fun baixaAlvaraConstrucao() {
        //caminho onde o arquivo vai ser baixado
        val filePath = "C:\\Users\\TheBabaYaga\\Downloads\\"
        val webClient = WebClient()
        webClient.options.isJavaScriptEnabled = false;
        webClient.options.isCssEnabled = false;

        try {
            val page1: HtmlPage = webClient.getPage("http://www5.curitiba.pr.gov.br/gtm/pmat_alvaraconstrucao/Default.aspx")
            val form = page1.getFormByName("Form1")
            val button: HtmlSubmitInput = form.getInputByName("btnGerarRelatorio")
            val page2 = button.click<Page>()

            val inputStream = page2.webResponse.contentAsStream

            // Salva o arquivo .xls
            val outputStream = FileOutputStream(filePath + "relatorioMensalAlvara")
            inputStream.copyTo(outputStream)
            outputStream.close()
            inputStream.close()

            //converte()

            webClient.close()
            println("Download realizado com sucesso")
            Thread.sleep(10000)
            gravaDadosDoAlvara()
        } catch (e: Exception) {
            println("Não foi possível fazer o download: erro -> ${e.printStackTrace()}")
        }
    }

    fun extrairDados(inputStream: InputStream): List<AlvaraConstrucaoDTO> {
        val alvaraList = mutableListOf<AlvaraConstrucaoDTO>()
        val doc = Jsoup.parse(inputStream, "UTF-8", "")
        val table = doc.select("table").first()

        table?.let {
            val rows = it.select("tr")
            for (row in rows) {
                val cols = row.select("td")
                if (cols.size > 0) {
                    val dto = criarDTO(cols)
                    alvaraList.add(dto)
                }
            }
        }

        return alvaraList
    }

    fun gravaDadosDoAlvara() {
        val alvaraList = mutableListOf<AlvaraConstrucaoDTO>()
        try {
            val file = File("C:\\Users\\TheBabaYaga\\Downloads\\relatorioMensalAlvara.xls")

            val fs = POIFSFileSystem(FileInputStream(file))
            val wb = HSSFWorkbook(fs)
            val planilha = wb.getSheetAt(0)
            val linhas = planilha.physicalNumberOfRows

            // Lê cada linha e cria um DTO pra cada linha
            for (linha in 1 until linhas) {
                val row = planilha.getRow(linha)
                if (row != null) {
                    alvaraRepository.findByNumeroAlvara(dataFormatter.formatCellValue(row.getCell(13)))
                        .let { alvaraEntity ->
                            if (alvaraEntity == null) {
                                val dto = criarDTO(row)
                                alvaraList.add(dto)
                            }
                        }
                }
            }
        } catch (ioe: Exception) {
            ioe.printStackTrace()
        }

        if (alvaraList.isNotEmpty()) {
            alvaraList.forEach { println(it) }
            salvaAlvara(alvaraList)
        }
    }

    fun salvaAlvara(alvara: List<AlvaraConstrucaoDTO>) {
        alvara.forEach {
            alvaraRepository.save(alvaraMapper.toAlvaraConstrucaoEntity(it))
        }
    }

    fun criarDTO(row: HSSFRow): AlvaraConstrucaoDTO {
        return AlvaraConstrucaoDTO(
            dataFormatter.formatCellValue(row.getCell(0)) ?: "",
            row.getCell(1)?.toString() ?: "",
            row.getCell(2)?.toString() ?: "",
            row.getCell(3)?.toString() ?: "",
            row.getCell(4)?.toString() ?: "",
            row.getCell(5)?.toString() ?: "",
            dataFormatter.formatCellValue(row.getCell(6)),
            row.getCell(7)?.toString() ?: "",
            row.getCell(8)?.toString() ?: "",
            row.getCell(9)?.toString() ?: "",
            dataFormatter.formatCellValue(row.getCell(10)),
            dataFormatter.formatCellValue(row.getCell(11)),
            dataFormatter.formatCellValue(row.getCell(12)),
            dataFormatter.formatCellValue(row.getCell(13)),
            row.getCell(14)?.toString() ?: "",
            row.getCell(15)?.toString() ?: "",
            row.getCell(16)?.toString() ?: "",
            row.getCell(17)?.toString() ?: "",
            dataFormatter.formatCellValue(row.getCell(18)),
            dataFormatter.formatCellValue(row.getCell(19)),
            dataFormatter.formatCellValue(row.getCell(20)),
            dataFormatter.formatCellValue(row.getCell(21)),
            dataFormatter.formatCellValue(row.getCell(22)),
            dataFormatter.formatCellValue(row.getCell(23)),
            dataFormatter.formatCellValue(row.getCell(24)),
            dataFormatter.formatCellValue(row.getCell(25)),
            row.getCell(26)?.toString() ?: "",
            row.getCell(27)?.toString() ?: "",
            row.getCell(28)?.toString() ?: "",
            row.getCell(29)?.toString() ?: "",
            row.getCell(30)?.toString() ?: "",
            row.getCell(31)?.toString() ?: "",
            row.getCell(32)?.toString() ?: "",
            row.getCell(33)?.toString() ?: ""
        )
    }*/

}