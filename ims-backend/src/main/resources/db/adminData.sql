insert into users values(
                         'f797f95c-a568-11ec-b909-0242ac120002',
                         'admin@gmail.com',
                         'JAVA',
                         'admin',
                         '$2a$12$Wua1evqRp69kdhWc4jNE1u9YVl3c39G0lO3OrLNccMhETFyFLfzGG',
                         'admin',
                         'admin'
                        );

insert into roles values(
                         'ba67533e-a568-11ec-b909-0242ac120002',
                         'ADMIN'
                        );
insert into roles values(
                            '4ae4650c-a5d0-11ec-b909-0242ac120002',
                            'HR'
                        );
insert into user_roles values(
                              'f797f95c-a568-11ec-b909-0242ac120002',
                              'ba67533e-a568-11ec-b909-0242ac120002'
                             );