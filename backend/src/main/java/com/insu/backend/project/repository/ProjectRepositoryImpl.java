package com.insu.backend.project.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.project.response.ProjectListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.insu.backend.project.entity.QProject.project;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public PageResponse<ProjectListResponse> getList(PageSearchDto projectSearch) {
        int offset = (projectSearch.getPage() - 1) * projectSearch.getSize(); // 0, 10, 20, 30
        int size = projectSearch.getSize();

        List<ProjectListResponse> projects = jpaQueryFactory
                .select(
                        Projections.constructor(ProjectListResponse.class,
                                project.projectNo,
                                project.projectTitle,
                                project.member.memberId,
                                project.createAt,
                                project.updateAt
                        )
                )
                .from(project)
                .where(
                        categoryEq(projectSearch.getCategory()),
                        keywordLike(projectSearch.getKeyword()),
                        memberEq(projectSearch.getAuthor()),
                        isClosedEq(projectSearch.getIsClosed()),
                        project.isDeleted.eq("N")
                )
                .orderBy(project.projectNo.desc())
                .offset(offset)
                .limit(size)
                .fetch();

        Long totalCount = Optional.ofNullable(jpaQueryFactory
                .select(project.count())
                .from(project)
                .where(
                        categoryEq(projectSearch.getCategory()),
                        keywordLike(projectSearch.getKeyword()),
                        memberEq(projectSearch.getAuthor()),
                        isClosedEq(projectSearch.getIsClosed())
                )
                .fetchOne()).orElse(0L); // count() 결과가 null일 경우 0으로 처리 (Optional 처리

        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean last = projectSearch.getPage() == totalPages;

        return PageResponse.<ProjectListResponse>builder()
                .content(projects)
                .page(projectSearch.getPage())
                .size(size)
                .totalCount(totalCount)
                .totalPages(totalPages)
                .last(last)
                .build();
    }

    private BooleanExpression categoryEq(String category) {
        return "all".equals(category) ? null : project.category.eq(category);
    }

    private BooleanExpression keywordLike(String keyword) {
        return StringUtils.hasText(keyword) ? project.projectTitle.likeIgnoreCase("%" + keyword + "%") : null;
    }

    private BooleanExpression memberEq(String memberId) {
        return StringUtils.hasText(memberId) ? project.member.memberId.eq(memberId) : null;
    }

    private BooleanExpression isClosedEq(String isClosed) {
        return "all".equals(isClosed) ? null : project.isClosed.eq(isClosed);
    }
}
