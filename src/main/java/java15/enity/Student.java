package java15.enity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "courses")
public class Student {
    @Id
    @GeneratedValue(generator = "student_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seg", allocationSize = 1)

    private Long id;

    @Column(name = "full_name", nullable = false, unique = true)
    private String full_name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollment_date;


    public Student(String full_name, String email, LocalDate enrollment_date) {
        this.full_name = full_name;
        this.email = email;
        this.enrollment_date = enrollment_date;
    }

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
}
