#create the database and use it
drop database project;
create database project;
use project;
#
#
#

create table users(user_id int AUTO_INCREMENT, user varchar(20), password varchar(20), full_name varchar(40), admin int, primary key(user_id) );
create table songs(song_id int AUTO_INCREMENT, song_name varchar(75), artist varchar(75), album varchar(75), genre varchar(50), votes int, primary key(song_id) );
create table play_lists(user_id int, song_id int, primary key(user_id, song_id), foreign key (user_id) references users (user_id), foreign key (song_id) references songs (song_id) );

