package com.sgiem.ms.employees.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "employees")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String names;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String email;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Formato de fecha inválido. Utilice el formato dd-MM-yyyy")
    private String dateOfbirth;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Formato de fecha inválido. Utilice el formato dd-MM-yyyy")
    private String createAccount;

    @NotNull
    @NotEmpty
    @Size(max = 30)
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Formato de fecha inválido. Utilice el formato dd-MM-yyyy")
    private String modifiedAccount;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String surenames;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String code;

    @NotNull
    @NotEmpty
    @Size(max = 40)
    private String rol;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String direction;

    @NotNull
    @Size(min = 9, max = 9)
    private String telephone;

    @NotNull
    private String age;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String state;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String profile;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String isNew;

    @NotNull
    @Size(min = 9, max = 30)
    private String nacionalidad;

    @NotNull
    @Size(min = 9, max = 30)
    private String genero;

    @NotNull
    @Size(min = 9, max = 30)
    private String estadocivil;

    @NotNull
    @Size(min = 8, max = 8)
    private String dni;

    @NotNull
    @Size(min = 8, max = 100)
    private File cv;

}
