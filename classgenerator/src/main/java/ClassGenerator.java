import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

public class ClassGenerator {

    private static final Set<String> MAPPERS = Set.of("BackgroundTaskMapperTest", "ChargeMapperTest", "CoreAddressMapperTest",
            "CoreCaseTempMapperTest", "CoreCommentMapperTest", "CoreContactInfoMapperTest", "CoreDocumentMapperTest",
            "CoreFileStorageMapperTest", "CoreLoadHistoryMapperTest", "CorePersonDocumentMapperTest", "CoreProcedureMapperTest",
            "CoreSmevRequestMapperTest", "CoreSmevResponseMapperTest", "CoreUploadedApplicationMapperTest",
            "CorrectionLogMapperTest", "EmploymentMapperTest", "ExternalInteractionMapperTest",
            "PaymentMapperTest", "PpotOpenCaseMapperTest", "UserTaskMapperTest");

    private static final List<String> SYSTEM_CODES = List.of("Vis", "Rfp", "Inv", "Cit", "Fpa", "Reg");

    private static final byte[] NEW_LINE_SYMBOL = "\n".getBytes(StandardCharsets.UTF_8);

    public static void main(String[] args) {
        createAllFiles(SYSTEM_CODES.get(1));
    }

    private static void createAllFiles(String systemCode) {
        for(String mapper : MAPPERS) {
            createFile(systemCode, mapper);
        }
    }

    @SneakyThrows
    private static void createFile(String system, String mapper) {
        String fileName = system + mapper;
        File file = new File(fileName + ".java");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);

        String packageValue = "package ru.atc.mvd.gismu.common.core.dao;\n";
        fos.write(packageValue.getBytes(StandardCharsets.UTF_8));

        fos.write(NEW_LINE_SYMBOL);

        String firstImport = "import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;\n";
        fos.write(firstImport.getBytes(StandardCharsets.UTF_8));

        String secondImport = "import ru.atc.mvd.gismu.rfp.testconfig.MyBatisMapperConfiguration;\n";
        fos.write(secondImport.getBytes(StandardCharsets.UTF_8));

        fos.write(NEW_LINE_SYMBOL);

        String config = "@SpringJUnitConfig(MyBatisMapperConfiguration.class)\n";
        fos.write(config.getBytes(StandardCharsets.UTF_8));

        String mainLine = String.format("public class %s extends %s {\n", fileName, mapper);
        fos.write(mainLine.getBytes(StandardCharsets.UTF_8));

        String bracket = "}";
        fos.write(bracket.getBytes(StandardCharsets.UTF_8));
    }
}
