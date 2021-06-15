package com.cts.entities;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NonNull
	private String name;

	@NonNull
	@Column(name = "SAL")
	private Long salary;

	@NonNull
	@Basic(fetch = FetchType.LAZY)
	private String comments;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPT_ID")
	private Department department;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToOne
	@JoinColumn(name = "P_SPACE")
	private ParkingSpace parkingSpace;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Default
	@ManyToMany
	@JoinTable(name = "EMP_PROJ", joinColumns = @JoinColumn(name = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PROJ_ID"))
	private List<Project> projects = new ArrayList<>();

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@Default
	@OneToMany
	@JoinTable(name = "EMP_PHONE", joinColumns = @JoinColumn(name = "EMP_ID"), inverseJoinColumns = @JoinColumn(name = "PHONE_ID"))
	private List<Phone> phones = new ArrayList<>();

}
