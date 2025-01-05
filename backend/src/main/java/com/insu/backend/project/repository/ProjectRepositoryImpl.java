package com.insu.backend.project.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.project.request.ProjectSearch;
import com.insu.backend.project.response.ProjectList;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.insu.backend.project.entity.QProject.project;

@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public PageResponse<ProjectList> getList(ProjectSearch projectSearch) {
        int offset = (projectSearch.getPage() - 1) * projectSearch.getSize(); // 0, 10, 20, 30
        int size = projectSearch.getSize();

        List<ProjectList> projects = jpaQueryFactory
                .select(
                        Projections.constructor(ProjectList.class,
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
                        memberEq(projectSearch.getMemberId()),
                        isClosedEq(projectSearch.getIsClosed()),
                        project.isDeleted.eq("N")
                )
                .orderBy(project.projectNo.desc())
                .offset(offset)
                .limit(size)
                .fetch();

        Long totalCount = jpaQueryFactory
                .select(project.count())
                .from(project)
                .where(
                        categoryEq(projectSearch.getCategory()),
                        keywordLike(projectSearch.getKeyword()),
                        memberEq(projectSearch.getMemberId()),
                        isClosedEq(projectSearch.getIsClosed())
                )
                .fetchOne();

        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean last = projectSearch.getPage() == totalPages;

        return new PageResponse<ProjectList>(projects, projectSearch.getPage(), size, totalCount, totalPages, last);
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
