package com.demobatch.schedule;


import com.demobatch.dto.DistrictCountDTO;
import com.demobatch.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MyJob {

    @Autowired
    private MainService mainService;

    @Scheduled(
            cron = "0 40 5 * * *"
    )
    public synchronized void shopViewCountToCsv() throws IOException {
        List<DistrictCountDTO> list = mainService.selectResult();

        LocalDateTime date = LocalDateTime.now();
        String nowDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));

        String filePath = "C:\\국비수업\\"+nowDate+"_통계.csv";
        File file = new File(filePath);


//        if(!file.exists()){
//            file.createNewFile();
//        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
        bufferedWriter.write("시/군/구,총조회수");
        bufferedWriter.newLine();

        for (DistrictCountDTO DTO: list) {
            StringBuilder data = new StringBuilder();
            data.append(DTO.getDistrictName());
            data.append(",");
            data.append(DTO.getViewCount());
            System.out.println("job_DTO : " + DTO);
            bufferedWriter.write(data.toString());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        // table 초기화
        mainService.execute();
    }
}
