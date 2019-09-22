package com.project.service;

import com.project.entity.Question;
import com.project.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    public List<Question> getQuestionList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(int id) {
        return this.questionRepository.findById(id).orElse(null);
    }

    public void saveQuestion(Question question) {
        this.questionRepository.save(question);
    }

    public void deleteQuestion(Question question) {
        this.questionRepository.delete(question);
    }
}
