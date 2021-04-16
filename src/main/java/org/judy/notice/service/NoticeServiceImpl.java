package org.judy.notice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.judy.common.util.NoticeFileDTO;
import org.judy.common.util.PageDTO;
import org.judy.notice.domain.Notice;
import org.judy.notice.dto.NoticeDTO;
import org.judy.notice.mapper.NoticeFileMapper;
import org.judy.notice.mapper.NoticeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeMapper mapper;

	private final NoticeFileMapper fileMapper;

	@Override
	public List<NoticeDTO> getList(PageDTO pageDTO) {

		log.info("getlist...............");

		return mapper.getList(pageDTO).stream().map(notice -> {
			NoticeDTO dto = toDTO(notice);
			return dto;
		}).collect(Collectors.toList());
	}

	@Override
	public NoticeDTO getOne(Integer nno) {

		return toDTO(mapper.getOne(nno));
	}

	@Override
	@Transactional
	public void insert(NoticeDTO dto) {

		log.info("dto:" + dto);

		dto.getList().forEach(file -> log.info("file:" + file.isImage()));

		log.info("insert...............");

		if (dto.getList() != null) {
			dto.getList().forEach(file -> {
				if (file.isImage()) {
					dto.setImg(true);
				} else {
					dto.setFile(true);
				}
			});
		}

		Notice vo = toDomain(dto);

		mapper.insert(vo);

		log.info("vo :" + vo);

		log.info("vo.getNno: " + vo.getNno());

		dto.getList().forEach(file -> {
			file.setNno(vo.getNno());
			fileMapper.insertFile(file);
		});

	}

	@Override
	public int getTotal(PageDTO pageDTO) {

		return mapper.getTotal(pageDTO);
	}

	@Override
	public void delete(Integer nno) {

		mapper.delete(nno);
	}

	@Override
	public List<NoticeFileDTO> getFile(Integer nno) {

		return fileMapper.getFile(nno);

	}

	@Override
	@Transactional
	public void update(NoticeDTO dto) {

		fileMapper.deleteFile(dto.getNno());

		List<NoticeFileDTO> list = dto.getList();

		dto.setImg(false);
		dto.setFile(false);

		if (dto.getList() != null) {
			for (NoticeFileDTO file : list) {

				if (file.isImage()) {
					dto.setImg(true);
				} else {
					dto.setFile(true);
				}
				file.setNno(dto.getNno());
				fileMapper.insertFile(file);
			}
			;

			mapper.update(toDomain(dto));
		}

	}

	@Override
	public NoticeFileDTO getThumb(Integer nno) {
		return mapper.getThumb(nno);
	}

	@Override
	public List<NoticeDTO> topList() {

		return mapper.topList().stream().map(notice -> toDTO(notice)).collect(Collectors.toList());
	}

}
