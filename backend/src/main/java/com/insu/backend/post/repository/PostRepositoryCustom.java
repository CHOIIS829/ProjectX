package com.insu.backend.post.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.post.response.PostListResponse;

public interface PostRepositoryCustom {

    PageResponse<PostListResponse> getList(PageSearchDto projectSearch);
}
