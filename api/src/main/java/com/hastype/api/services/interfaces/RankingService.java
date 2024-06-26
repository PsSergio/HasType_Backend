package com.hastype.api.services.interfaces;

import com.hastype.api.models.QuizModel;
import com.hastype.api.models.RankingTempoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RankingService {

    public void addUserRanking(QuizModel quiz);

    public void updateUserInRanking(QuizModel quiz);

}
