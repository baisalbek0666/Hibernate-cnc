package java15.enity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "students")

public class Course {
    @Id
    @GeneratedValue(generator = "course_gen", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seg", allocationSize = 1)


    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))

            private List<Student> students;

}

