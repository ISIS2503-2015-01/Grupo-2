 

   ALTER TABLE APP.DoctorpacienteDelDoctorEntity ADD FOREIGN KEY (doctorId) REFERENCES  APP.DoctorEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.DoctorpacienteDelDoctorEntity ADD FOREIGN KEY (pacienteDelDoctorId) REFERENCES  APP.PacienteEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
		
   ALTER TABLE APP.PacientereporteDelPacienteEntity ADD FOREIGN KEY (pacienteId) REFERENCES  APP.PacienteEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
   ALTER TABLE APP.PacientereporteDelPacienteEntity ADD FOREIGN KEY (reporteDelPacienteId) REFERENCES  APP.ReporteEntity(id) ON DELETE CASCADE ON UPDATE RESTRICT;
		
