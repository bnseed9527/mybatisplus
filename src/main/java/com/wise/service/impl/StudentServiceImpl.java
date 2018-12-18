package com.wise.service.impl;

import com.wise.entity.Student;
import com.wise.mapper.StudentMapper;
import com.wise.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * student 服务实现类
 * </p>
 *
 * @author YsCy丶
 * @since 2018-11-12
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
