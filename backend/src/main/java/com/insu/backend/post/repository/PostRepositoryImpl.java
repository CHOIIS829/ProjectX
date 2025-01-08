package com.insu.backend.post.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.post.response.PostListResponse;
import com.insu.backend.project.response.ProjectListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.insu.backend.post.entity.QPost.post;
import static com.insu.backend.project.entity.QProject.project;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public PageResponse<PostListResponse> getList(PageSearchDto postSearch) {
        int offset = (postSearch.getPage() - 1) * postSearch.getSize(); // 0, 10, 20, 30
        int size = postSearch.getSize();

        List<PostListResponse> posts = jpaQueryFactory
                .select(
                        Projections.constructor(PostListResponse.class,
                                post.postNo,
                                post.postTitle,
                                post.member.memberId,
                                post.createAt,
                                post.updateAt
                        )
                )
                .from(post)
                .where(
                        categoryEq(postSearch.getCategory()),
                        keywordLike(postSearch.getKeyword()),
                        memberEq(postSearch.getAuthor()),
                        post.isDeleted.eq("N")
                )
                .orderBy(post.postNo.desc())
                .offset(offset)
                .limit(size)
                .fetch();

        Long totalCount = jpaQueryFactory
                .select(post.count())
                .from(post)
                .where(
                        categoryEq(postSearch.getCategory()),
                        keywordLike(postSearch.getKeyword()),
                        memberEq(postSearch.getAuthor())
                )
                .fetchOne();

        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean last = postSearch.getPage() == totalPages;

        return PageResponse.<PostListResponse>builder()
                .content(posts)
                .page(postSearch.getPage())
                .size(size)
                .totalCount(totalCount)
                .totalPages(totalPages)
                .last(last)
                .build();
    }

    private BooleanExpression categoryEq(String category) {
        return "all".equals(category) ? null : post.category.eq(category);
    }

    private BooleanExpression keywordLike(String keyword) {
        return StringUtils.hasText(keyword) ? post.postTitle.likeIgnoreCase("%" + keyword + "%") : null;
    }

    private BooleanExpression memberEq(String memberId) {
        return StringUtils.hasText(memberId) ? post.member.memberId.eq(memberId) : null;
    }
}
