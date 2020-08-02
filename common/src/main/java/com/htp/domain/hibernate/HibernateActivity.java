package com.htp.domain.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "m_activity")
public class HibernateActivity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "group_id")
	private Long groupId;

	@Column(name = "level_id")
	private Long levelId;

	@Column(name = "time_start")
	private Date timeStart;

	@Column(name = "time_end")
	private Date timeEnd;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "state_id", nullable = false)
	private HibernateState stateId;

	@Column(name = "training_id")
	private Long trainingId;

	@Column(name = "is_deleted")
	private boolean isDeleted;

}
