package com.zzaug.review.domain.dto.review.query;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ViewMemberReviewUseCaseRequest {
	private Long authorId;
}
