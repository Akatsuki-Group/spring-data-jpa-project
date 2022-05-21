package org.example.spec;

import lombok.*;
/**
 * @author jack
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
	private String key;
	private Operator operation;
	private Object value;
}