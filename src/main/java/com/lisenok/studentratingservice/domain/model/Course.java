package com.lisenok.studentratingservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "Course"),
        @NamedEntityGraph(name = "Course.students-lessons",
                attributeNodes = {
                    @NamedAttributeNode("students"),
                    @NamedAttributeNode("lessons")
                }
        ),
        @NamedEntityGraph(name = "Course.students",
                attributeNodes = {
                        @NamedAttributeNode("students")
                }
        ),
        @NamedEntityGraph(name = "Course.lessons",
                attributeNodes = {
                        @NamedAttributeNode("lessons")
                }
        )
})
public class Course {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active")
    private boolean isActive;

    /**
     * В данном случае Course - "owning side", то есть добавление студента в список студентов курса
     * повлияет на связанную таблицу, а добавление курса в список курсов у студента - нет.
     * При необходимости можно создать отдельную модель, по типу CourseStudentKey, тогда добавление будет работать
     * с обемх сторон
     */
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "courses_students",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Student> students;

    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons;

    public void addStudent(Student student) {
        if (students == null) students = new HashSet<>();
        students.add(student);
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) lessons = new HashSet<>();
        lessons.add(lesson);
    }

}
