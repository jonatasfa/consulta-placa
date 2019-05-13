package br.com.i2assessoria.consultaplaca.service;

import br.com.i2assessoria.consultaplaca.entity.Veiculo;
import br.com.i2assessoria.consultaplaca.exception.FileStorageException;
import br.com.i2assessoria.consultaplaca.exception.MyFileNotFoundException;
import br.com.i2assessoria.consultaplaca.property.FileStorageProperties;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final VeiculoService veiculoService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties, VeiculoService veiculoService) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        this.veiculoService = veiculoService;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = "placas.xlsx";

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            atualizaBase(targetLocation.toString());

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource() {
        String fileName = "placas.xlsx";
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public void atualizaBase(String arquivo){
        try {

            List<Veiculo> veiculoList = new ArrayList<>();

            File file = new File(arquivo);
            String name = file.toString();
            int pos = name.lastIndexOf('.');
            String ext = name.substring(pos + 1);
            FileInputStream fileIn = new FileInputStream(file);
            Workbook obj = null;

            if (ext.equals("xlsx")) {
                try {
                    obj = new XSSFWorkbook(fileIn);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else if (ext.equals("xls")) {
                try {
                    obj = new HSSFWorkbook(fileIn);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else
            {
                throw new IllegalArgumentException("Received file does not have a standard excel extension.");
            }

            Sheet worksheet = obj.getSheetAt(0);
            for(int i=0;i<=worksheet.getLastRowNum();i++){
                try{

                    if (!worksheet.getRow(i).getCell(0).getStringCellValue().equals("Placa")){

                        veiculoList
                                .add(new Veiculo(
                                        worksheet.getRow(i).getCell(0).getStringCellValue(),
                                        worksheet.getRow(i).getCell(1).getStringCellValue(),
                                        worksheet.getRow(i).getCell(2).getStringCellValue(),
                                        worksheet.getRow(i).getCell(3).getStringCellValue(),
                                        worksheet.getRow(i).getCell(4).getStringCellValue(),
                                        worksheet.getRow(i).getCell(5).getStringCellValue(),
                                        worksheet.getRow(i).getCell(6).getStringCellValue(),
                                        worksheet.getRow(i).getCell(7).getDateCellValue(),
                                        worksheet.getRow(i).getCell(8).getStringCellValue(),
                                        worksheet.getRow(i).getCell(9).getStringCellValue(),
                                        worksheet.getRow(i).getCell(10).getStringCellValue(),
                                        NumberToTextConverter.toText(worksheet.getRow(i).getCell(11).getNumericCellValue()),
                                        NumberToTextConverter.toText(worksheet.getRow(i).getCell(12).getNumericCellValue()),
                                        NumberToTextConverter.toText(worksheet.getRow(i).getCell(13).getNumericCellValue()),
                                        worksheet.getRow(i).getCell(14).getStringCellValue(),
                                        worksheet.getRow(i).getCell(15).getStringCellValue(),
                                        worksheet.getRow(i).getCell(16).getStringCellValue(),
                                        NumberToTextConverter.toText(worksheet.getRow(i).getCell(17).getNumericCellValue()),
                                        worksheet.getRow(i).getCell(18).getStringCellValue(),
                                        worksheet.getRow(i).getCell(19).getStringCellValue(),
                                        worksheet.getRow(i).getCell(20).getStringCellValue(),
                                        worksheet.getRow(i).getCell(21).getStringCellValue()));

                    }

                }catch (Exception e){
                    System.out.println("Erro ao inserir linha da planilha. " + e.getMessage());
                }
            }

            veiculoService.removeAll();
            veiculoService.insertAll(veiculoList);

            System.out.println("Planilha atualizada");
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado");
        }
    }
}
