package com.neytech.mscreditappraiser.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvaluationReturn {

	private List<ApprovedCard> cards = new ArrayList<>();
}
