create table loan
(
	loanId integer AUTO_INCREMENT,
   name varchar(100) not null,
   ssn long(100),
   dob date,
   principal double,
   rate double,
   type varchar(10),
   days int,
   apr double,
   primary key(loanId)
);