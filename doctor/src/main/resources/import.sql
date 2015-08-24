insert into user(id, username, password) values (1, 'user1', 'password');
insert into user(id, username, password) values (2, 'user2', 'password');

insert into channel (id, name) values (1, 'channel1');
insert into channel (id, name) values (2, 'channel2');

insert into join_channel(user_id, channel_id, last_read_message_id) values (1, 1, null)
insert into join_channel(user_id, channel_id, last_read_message_id) values (1, 2, null)

insert into message(channel_id, user_id, text, sequence, message_type) values (1, 1, 'hello1', 1, 'PLAIN')
insert into message(channel_id, user_id, text, sequence, message_type) values (1, 1, 'hello2', 2, 'PLAIN')
insert into message(channel_id, user_id, text, sequence, message_type) values (1, 1, 'hello3', 3, 'PLAIN')
insert into message(channel_id, user_id, text, sequence, message_type) values (1, 1, 'new channel', 4, 'NEW_CHANNEL')