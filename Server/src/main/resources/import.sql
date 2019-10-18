insert into users (user_id, email, password, username) Values(user_seq_id.NEXTVAL, 'gvega@gmail.com', 'password', 'gvega123');
insert into accounts (account_id, aboutMe, country, state, status) Values(account_seq_id.NEXTVAL, 'Test User 1', 'USA', 'NY', 1);
commit;

insert into users (user_id, email, password, username) Values(user_seq_id.NEXTVAL, 'jWerenski@gmail.com', 'p4ssword', 'coolJack');
insert into accounts (account_id, aboutMe, country, state, status) Values(account_seq_id.NEXTVAL, 'Test User 2', 'USA', 'MI', 2);
commit;

insert into users (user_id, email, password, username) Values(user_seq_id.NEXTVAL, 'Fred@gmail.com', 'b3stpassw0rd', 'freddyfred');
insert into accounts (account_id, aboutMe, country, state, status) Values(account_seq_id.NEXTVAL, 'Test User 3', 'USA', 'CA', 3);
commit;

insert into channels (channel_id, open) Values(channel_seq_id.NEXTVAL, true);
insert into channels (channel_id, open) Values(channel_seq_id.NEXTVAL, true);
commit;

insert into messages (message_id, body, author, createdStamp, destination) Values (message_seq_id.NEXTVAL, 'Hi', 1, CURRENT_TIMESTAMP, 1);
insert into messages (message_id, body, author, createdStamp, destination) Values (message_seq_id.NEXTVAL, 'Hey', 1, CURRENT_TIMESTAMP+5000, 2);
insert into messages (message_id, body, author, createdStamp, destination) Values (message_seq_id.NEXTVAL, 'Hi there', 2, CURRENT_TIMESTAMP+5100, 2);
insert into messages (message_id, body, author, createdStamp, destination) Values (message_seq_id.NEXTVAL, 'whats up', 3, CURRENT_TIMESTAMP+5100, 2);
insert into messages (message_id, body, author, createdStamp, destination) Values (message_seq_id.NEXTVAL, '???', 1, CURRENT_TIMESTAMP+10000, 1);
commit;
