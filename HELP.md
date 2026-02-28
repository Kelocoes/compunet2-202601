# Actividad en clase: Diagrama de base de datos

En esta actividad tendrán que modelar la base de datos planteada en este documento usando la libería de Spring Data JPA. Para esto, deben crear las clases de entidad correspondientes a cada tabla, definiendo las relaciones entre ellas utilizando anotaciones como `@OneToMany`, `@ManyToOne`, `@ManyToMany`, etc.

Posterior a esto, deberán generar con ayuda de la IAG datos de prueba para cada una de las tablas, asegurándose de que las relaciones entre ellas se mantengan correctamente. Para ello usarán el archivo `data.sql` ubicado en el directorio `src/main/resources` de su proyecto Spring Boot. Este archivo se ejecutará automáticamente al iniciar la aplicación, insertando los datos de prueba en la base de datos.

Finalmente, deberán hacer algunas consultas a la base de datos para verificar que los datos se han insertado correctamente y que las relaciones entre las tablas funcionan como se espera. Las consultas serán las siguientes:

1. Obtener todas las encuestas junto con sus secciones y preguntas.
2. Obtener todas las respuestas de una entrevista específica.
3. Obtener todas las opciones disponibles para una pregunta específica.
4. Obtener todas las preguntas de una encuesta.
5. Obtener todas los resultados de una encuesta ordenadas por fecha
6. Obtener preguntas por tipo
7. Buscar encuestas por nombre (like)
8. Obtener respuestas de una pregunta específica en todas las entrevistas
9. Obtener secciones raíz (que no tienen sección padre) de una encuesta específica.
10. Obtener las opciones de un grupo específico.
11. Obtener todas las opciones de una pregunta consultada través de la tabla `option_question`.
12. Obtener la primera entrevista realizada para una encuesta específica.
13. Obtener el número total de entrevistas realizadas para cada encuesta.
14. Comprobar si existe alguna entrevista realizada por un entrevistador específico.
15. Obtener todas las preguntas de un tipo específico (por ejemplo, todas las preguntas de tipo "multiple choice").


```bash
// ========================
// TABLES
// ========================

Table survey {
  id int [pk]
  name varchar
  intro text
  validation varchar
  image_url varchar
  outro text
  styles text
}

Table section {
  id int [pk]
  title varchar
  description text
  flag varchar
  order_col int
  section_id int
  type varchar
  survey_id int
  background_image varchar
}

Table question {
  id int [pk]
  name varchar
  question_id int
  type varchar
  section_id int
  order_col int
}

Table option {
  id int [pk]
  name varchar
  type varchar
  group_id int
}

Table option_group {
  id int [pk]
  name varchar
}

Table option_question {
  id int [pk]
  question_id int
  option_id int
  group_id int
}

Table answer {
  id int [pk]
  answer text
  question_id int
  interview_id int
}

Table interview {
  id int [pk]
  time_start timestamp
  survey_id int
  interviewer_id int
  time_end timestamp
  username varchar
  institution_id int
}

Table type {
  id int [pk]
  name varchar
  description text
  table_name varchar
}

// ========================
// RELATIONSHIPS
// ========================

Ref: section.survey_id > survey.id
Ref: section.section_id > section.id

Ref: question.section_id > section.id
Ref: question.question_id > question.id

Ref: option.group_id > option_group.id

Ref: option_question.question_id > question.id
Ref: option_question.option_id > option.id
Ref: option_question.group_id > option_group.id

Ref: answer.question_id > question.id
Ref: answer.interview_id > interview.id

Ref: interview.survey_id > survey.id

Ref: question.type > type.name
```