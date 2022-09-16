select id from guilds where
      time_message=(select MAX(time_message) from guilds) and
      time_message>=CURDATE();

select * from bestmembers
        where (guild_id=1013860197233610823 and time_message=(select curdate()))
        and count=(select max(count) from bestmembers where
        time_message=(select curdate()) and guild_id=1013860197233610823);