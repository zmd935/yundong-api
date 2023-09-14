CREATE DEFINER=`root`@`localhost` PROCEDURE `TruncateTable`()
BEGIN
	DECLARE DBName VARCHAR(256) DEFAULT 'clkj-frame';
    DECLARE excludeTables VARCHAR(256) DEFAULT 'opt_terms,pay_rate_type,sys_menu,sys_config,uc_menu,uc_role,uc_role_menu';
  DECLARE strClear VARCHAR(256);
    DECLARE done INT DEFAULT 0;

    #定义游标
    DECLARE curOne CURSOR FOR SELECT CONCAT('TRUNCATE TABLE ',TABLE_NAME,';') as ClearTable FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = DBName AND NOT FIND_IN_SET(TABLE_NAME,excludeTables);
    #绑定控制变量到游标,游标循环结束自动转为True
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
    #打开游标
    OPEN curOne;

    REPEAT
        FETCH curOne INTO strClear;
        #动态执行SQL语句
        SET @mysql = strClear;
        PREPARE stmt from @mysql;
        EXECUTE stmt;
        DEALLOCATE PREPARE stmt;

        UNTIL done = 1 END REPEAT;

    #关闭游标
    CLOSE curOne;
    #插入admin
    INSERT INTO `clkj-frame`.`sys_user`(`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`, `create_time`) VALUES (1, 'admin', '6d85a2c70f5a3b6d4bc85bdf07a761847f17ae3d4c07b2be0b826c7cfa1c1408', 'cmM6pOT68ZJXzdTfV4QC', 'root@clkj.com', '13612345678', 1, 1, '2016-11-11 11:11:11');

END
