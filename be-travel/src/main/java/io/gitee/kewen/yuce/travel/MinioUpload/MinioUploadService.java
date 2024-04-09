package io.gitee.kewen.yuce.travel.MinioUpload;

import io.gitee.kewen.yuce.common.model.dto.req.TravelDeleteReq;
import io.gitee.kewen.yuce.common.model.dto.req.WriteTravelReq;
import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/5 15:32
 */
public interface MinioUploadService {
    Boolean upload(MultipartFile[] files , WriteTravelReq req) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;

    Boolean deleteTravel(List<TravelDeleteReq> reqs) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException;
}
