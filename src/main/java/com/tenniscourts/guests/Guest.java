package com.tenniscourts.guests;

import com.tenniscourts.config.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Guest extends BaseEntity<Long> {

  /**
	 * 
	 */
	private static final long serialVersionUID = -5214019933381446324L;

  @Id
  @Column
  private Long id;
  
  @Column
  @NotNull
  private String name;

}
