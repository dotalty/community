package com.atschool.study.community.service;

import com.atschool.study.community.dto.QuestionDTO;
import com.atschool.study.community.mapper.QuestionMapper;
import com.atschool.study.community.mapper.UserMapper;
import com.atschool.study.community.model.Question;
import com.atschool.study.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question>questions = questionMapper.list();
        List<QuestionDTO>questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
