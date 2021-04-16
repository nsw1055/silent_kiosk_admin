package org.judy.common.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.judy.common.mapper.OldFileMapper;
import org.judy.common.util.NoticeFileDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
@RequiredArgsConstructor
public class FileCheckTask {

	private final OldFileMapper mapper;

	private String getFolderYesterDay() {

		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, -1);

		String str = sdf.format(cal.getTime());

		return str.replace("-", File.separator);

	}

	@Scheduled(cron = "0 0 2 * * *")
	public void checkFiles() throws Exception {

		log.warn("File Check Task run.....");
		log.warn(new Date());

		List<NoticeFileDTO> fileList = mapper.getOldFiles();

		List<Path> fileListPaths = fileList.stream().map(vo -> Paths.get("C:\\upload\\admin\\notice",
				vo.getUploadPath(), "\\" + vo.getUuid() + "_" + vo.getFileName())).collect(Collectors.toList());

		fileList.stream().filter(vo -> vo.isImage() == true).map(vo -> Paths.get("C:\\upload\\admin\\notice",
				vo.getUploadPath(), "\\" + "s_" + vo.getUuid() + "_" + vo.getFileName()))
				.forEach(p -> fileListPaths.add(p));

		log.warn("==============================================");

		fileListPaths.forEach(p -> log.warn(p));

		File targetDir = Paths.get("C:\\upload\\admin\\notice", getFolderYesterDay()).toFile();

		File[] removeFiles = targetDir.listFiles(file -> fileListPaths.contains(file.toPath()) == false);

		for (File file : removeFiles) {

			log.warn(file.getAbsolutePath());

			file.delete();

		}
	}

	@Scheduled(cron = "0 0 2 * * *")
	public void deleteTemp() {

		String path = "C:\\upload\\temp\\admin";

		delFolder(path);

	}

	public static void delFolder(String path) {

		File folder = new File(path);
		try {
			if (folder.exists()) {
				File[] folderList = folder.listFiles();

				for (int i = 0; i < folderList.length; i++) {
					if (folderList[i].isFile()) {
						folderList[i].delete();
						log.info("File Delete.....");
					} else {
						delFolder(folderList[i].getPath());
						log.info("Folder Delete.....");
					}
					folderList[i].delete();
				}
				folder.delete();
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
