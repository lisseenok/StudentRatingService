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
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@NamedEntityGraphs(value = {
        @NamedEntityGraph(name = "Student"),
        @NamedEntityGraph(name = "Student.courses",
                attributeNodes = {
                        @NamedAttributeNode("courses"),
                }
        ),
        @NamedEntityGraph(name = "Student.ratings",
                attributeNodes = {
                        @NamedAttributeNode("ratings")
                }
        ),
        @NamedEntityGraph(name = "Student.courses-grades-ratings",
                attributeNodes = {
                    @NamedAttributeNode("courses"),
                    @NamedAttributeNode("grades"),
                    @NamedAttributeNode("ratings")
                }
        )
})
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic_name")
    private String patronymicName;

    @Column(name = "group_number")
    private String groupNumber;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(mappedBy = "students", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Course> courses;

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;

    @OneToMany(mappedBy = "student")
    private Set<Rating> ratings;
}
