package com.ndtdoanh.file.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ndtdoanh.file.dto.FileInfo;
import com.ndtdoanh.file.entity.FileMgmt;

@Mapper(componentModel = "spring")
public interface FileMgmtMapper {
    @Mapping(target = "id", source = "name")
    FileMgmt toFileMgmt(FileInfo fileInfo);
}
