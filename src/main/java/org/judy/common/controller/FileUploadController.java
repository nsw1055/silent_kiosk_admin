package org.judy.common.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.judy.common.util.ManagerFileDTO;
import org.judy.common.util.NoticeFileDTO;
import org.judy.notice.service.NoticeService;
import org.judy.store.service.StoreService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/common")
@Log4j
@RequiredArgsConstructor
public class FileUploadController {

	private final StoreService storeService;

	private final NoticeService service;

	@GetMapping("/manager/view")
	public ResponseEntity<byte[]> view(String link) {

		String path = "C:\\upload\\temp\\admin\\manager";
		
		return imgView(link, path);
	}
	
	@GetMapping("/menu/view")
	public ResponseEntity<byte[]> menuView(String link) {
		log.info("link: "+link);
		String path = "C:\\upload\\admin\\manager\\MImg\\";

		return imgView(link, path);
	}
	
	@GetMapping("/topping/view")
	public ResponseEntity<byte[]> toppingView(String link) {
		log.info("link: "+link);
		String path = "C:\\upload\\admin\\manager\\tImg\\";

		return imgView(link, path);
	}
	
	@GetMapping("/logo/view")
	public ResponseEntity<byte[]> logoView(String link) {
		log.info("link: "+link);
		String path = "C:\\upload\\admin\\manager\\logoImg\\";
		
		return imgView(link, path);
	}

	@PostMapping(value = "/manager/upload", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ManagerFileDTO>> postUpload(MultipartFile[] files) {

		String path = "C:\\upload\\temp\\admin\\manager";

		List<ManagerFileDTO> fileList = new ArrayList<>();

		for (MultipartFile multipartFile : files) {

			log.info(multipartFile);
			log.info(multipartFile.getOriginalFilename());

			UUID uuid = UUID.randomUUID();

			String savePath = getFolder();

			File uploadPath = new File(path, getFolder());

			String fileName = uuid.toString() + "_" + multipartFile.getOriginalFilename();

			//String sFileName = "s_" + uuid.toString() + "_" + multipartFile.getOriginalFilename();

			//boolean isImage = multipartFile.getContentType().startsWith("image");

			if (uploadPath.exists() == false) {
				uploadPath.mkdirs();
			}

			File saveFile = new File(uploadPath, fileName);

			ManagerFileDTO fileDTO = ManagerFileDTO.builder().fileName(multipartFile.getOriginalFilename())
					.uploadPath(savePath).uuid(uuid.toString()).build();

			try {
				multipartFile.transferTo(saveFile);

				/*
				 * if (isImage) {
				 * 
				 * FileOutputStream fos = new FileOutputStream(new File(uploadPath, sFileName));
				 * 
				 * Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 90, 90);
				 * 
				 * fos.close(); }
				 */

				fileList.add(fileDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for

		return new ResponseEntity<List<ManagerFileDTO>>(fileList, HttpStatus.OK);

	}

	@PostMapping(value = "/manager/doc/upload", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ManagerFileDTO>> postDocUpload(MultipartFile[] files, String value) {

		String path = "C:\\upload\\temp\\admin\\manager";
		
			
		List<ManagerFileDTO> fileList = new ArrayList<>();

		for (MultipartFile multipartFile : files) {

			log.info(multipartFile);
			log.info(multipartFile.getOriginalFilename());

			UUID uuid = UUID.randomUUID();

			String savePath = getFolder();

			File uploadPath = new File(path, getFolder());

			String fileName = value+"_"+uuid.toString() + "_" + multipartFile.getOriginalFilename();

			//String sFileName = "s_" + uuid.toString() + "_" + multipartFile.getOriginalFilename();

			boolean isImage = multipartFile.getContentType().startsWith("image");

			if (uploadPath.exists() == false) {
				uploadPath.mkdirs();
			}

			File saveFile = new File(uploadPath, fileName);

			ManagerFileDTO fileDTO = ManagerFileDTO.builder().fileName(fileName)
					.uploadPath(savePath).uuid(uuid.toString()).image(isImage).build();

			try {
				multipartFile.transferTo(saveFile);

				/*
				 * if (isImage) {
				 * 
				 * FileOutputStream fos = new FileOutputStream(new File(uploadPath, sFileName));
				 * 
				 * Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 90, 90);
				 * 
				 * fos.close(); }
				 */

				fileList.add(fileDTO);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for

		return new ResponseEntity<List<ManagerFileDTO>>(fileList, HttpStatus.OK);

	}
	
	@GetMapping(value = "/manager/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadDoc(String link) {

		log.info("download file: " + link);

		String path = "C:\\upload\\temp\\admin\\manager";

		String str = "";

		File targetFile = encoding(link, path);

		log.info(targetFile);

		Resource resource = new FileSystemResource(targetFile);

		log.info("resource : " + resource);

		String resourceName = resource.getFilename();

		log.info(resourceName);

		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	// deleteFile

	@PostMapping(value = "/manager/delete")
	@ResponseBody
	public ResponseEntity<String> delFile(@RequestBody String link) {

		log.info("delete.................");
		String[] data = link.split("/");

		// uuid split
		
		log.info(data[1]);

		String[] muuidArray = data[1].split("_", 2);

		String muuid = muuidArray[0];

		log.info(muuid);
		
		String path = "C:\\upload\\temp\\admin\\manager";

		File file = encoding(link, path);

		log.info(link);
		
		file.delete();

		storeService.deleteDoc(muuid);

		return new ResponseEntity<String>("success", HttpStatus.OK);

	}
	
	// storeUpload
	@PostMapping("/store/upload")
	   public ResponseEntity<List<ManagerFileDTO>> storeUpload(MultipartFile[] uploadFile) {

	      String path = "C:\\upload\\temp\\admin\\manager";

	      log.info("upload------------------");

	      List<ManagerFileDTO> fileList = new ArrayList<>();

	      for (MultipartFile multipartFile : uploadFile) {

	         log.info("---------------------------");
	         log.info("upload file name: " + multipartFile.getOriginalFilename());
	         log.info("upload file size: " + multipartFile.getSize());

	         String folderPath = getFolder();

	         File uploadPath = new File(path, folderPath);

	         if (uploadPath.exists() == false) {

	            uploadPath.mkdirs();
	         }

	         UUID uuid = UUID.randomUUID();

	         String fileName = multipartFile.getOriginalFilename();

	         File saveFile = new File(uploadPath, uuid.toString() + "_" + fileName);

	         log.info(saveFile);
	         boolean isImage = multipartFile.getContentType().startsWith("image");

	         try {

	            
	               File sFile = new File(uploadPath, "s_" + uuid.toString() + "_" + fileName);
	               FileOutputStream fos = new FileOutputStream(sFile);
	               Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);

	            
	            ManagerFileDTO managerFile = new ManagerFileDTO(folderPath, uuid.toString(), fileName, isImage);

	            fileList.add(managerFile);

	            multipartFile.transferTo(saveFile);
	         } catch (Exception e) {
	            e.printStackTrace();
	         }

	      } // end for

	      return new ResponseEntity<>(fileList, HttpStatus.OK);
	   }
	
	// storeView
	@GetMapping("/store/view")
	   @ResponseBody
	   public ResponseEntity<byte[]> getStoreView(String link) {

		   log.info("view.....................");
		   log.info("Link:" + link);
		   
	      String path = "C:\\upload\\temp\\admin\\manager";

	      ResponseEntity<byte[]> result = null;

	      try {
	         File targetFile = encoding(link,path);
	         
	         HttpHeaders header = new HttpHeaders();

	         header.add("Content-Type", Files.probeContentType(targetFile.toPath()));

	         result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(targetFile), header, HttpStatus.OK);

	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return result;
	   }
	
	
	
	// ====================================================================================================================
	// notice
	@GetMapping("/notice/thumb")
	public ResponseEntity<byte[]> getThumb(Integer nno) {

		log.info("view.....................");

		String link = service.getThumb(nno).getthumbLink();

		String path = "C:\\upload\\admin\\notice";

		ResponseEntity<byte[]> result = null;

		try {
			File targetFile = encoding(link, path);

			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(targetFile.toPath()));

			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(targetFile), header, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@GetMapping("/notice/getFiles")
	@ResponseBody
	public ResponseEntity<List<NoticeFileDTO>> getFiles(Integer nno) {

		log.info("nno:........." + nno);

		return new ResponseEntity<>(service.getFile(nno), HttpStatus.OK);

	}

	@PostMapping("/notice/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> postDelete(@RequestBody NoticeFileDTO file) {

		log.info("link:" + file);

		String path = "C:\\upload\\temp\\admin\\notice";

		String fileName = file.getFileName();

		String filePath = file.getUploadPath();

		String uuid = file.getUuid();

		String getFile = filePath + File.separator + uuid + "_" + fileName;

		log.info(getFile);

		File targetFile = new File(path, getFile);

		log.info(targetFile);

		targetFile.delete();

		if (file.isImage()) {

			String thumbImg = filePath + File.separator + "s_" + uuid + "_" + fileName;

			File targetThumbFile = new File(path, thumbImg);

			targetThumbFile.delete();

		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@GetMapping("/notice/view")
	@ResponseBody
	public ResponseEntity<byte[]> getView(String link) {

		log.info("view.....................");
		log.info("Link:" + link);

		String path = "C:\\upload\\admin\\notice";

		return imgView(path,link);
	}

	@GetMapping("/notice/preview")
	@ResponseBody
	public ResponseEntity<byte[]> getPreview(String link) {

		log.info("view.....................");
		log.info("Link:" + link);

		String path = "C:\\upload\\temp\\admin\\notice";

		return imgView(path, link);
	}

	


	@PostMapping("/notice/upload")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<NoticeFileDTO>> uploadPost(MultipartFile[] uploadFile) {

		String path = "C:\\upload\\temp\\admin\\notice";

		log.info("upload------------------");

		List<NoticeFileDTO> fileList = new ArrayList<>();

		for (MultipartFile multipartFile : uploadFile) {

			log.info("---------------------------");
			log.info("upload file name: " + multipartFile.getOriginalFilename());
			log.info("upload file size: " + multipartFile.getSize());

			String folderPath = getFolder();

			File uploadPath = new File(path, folderPath);

			if (uploadPath.exists() == false) {

				uploadPath.mkdirs();
			}

			UUID uuid = UUID.randomUUID();

			String fileName = multipartFile.getOriginalFilename();

			File saveFile = new File(uploadPath, uuid.toString() + "_" + fileName);

			boolean isImage = multipartFile.getContentType().startsWith("image");

			try {

				if (isImage) {
					File sFile = new File(uploadPath, "s_" + uuid.toString() + "_" + fileName);
					FileOutputStream fos = new FileOutputStream(sFile);
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), fos, 100, 100);

				}
				NoticeFileDTO noticeFile = new NoticeFileDTO(folderPath, uuid.toString(), fileName, isImage);

				fileList.add(noticeFile);

				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} // end for

		return new ResponseEntity<>(fileList, HttpStatus.OK);
	}

	@GetMapping(value = "/notice/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String link) {

		String path = "C:\\upload\\admin\\notice";

		String str = "";

		try {
			str = URLDecoder.decode(link, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String fileLink = str.replace("#", ".");

		File viewFile = new File(path, fileLink);

		Resource resource = new FileSystemResource(viewFile);

		String resourceName = resource.getFilename();

		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);

	}

	private File encoding(String link, String path) {

		File viewFile = null;

		try {
			String str = URLDecoder.decode(link, "UTF-8");

			String fileLink = str.replace("#", ".");

			viewFile = new File(path, fileLink);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewFile;
	}
	
	private ResponseEntity<byte[]> imgView(String path,String link) {

		ResponseEntity<byte[]> result = null;

		File targetFile = encoding(link, path);

		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(targetFile.toPath()));
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(targetFile), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}