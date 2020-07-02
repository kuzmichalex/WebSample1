-1. Уникальные имена ролей
select distinct role_name
from m_roles;

--2 Подсчёт количаства машин у пользователей, включая без-машиных.
select usr.username || ' ' || usr.surname || ' : ' || count(car.id)
from m_users usr
         left join m_cars car on usr.id = car.user_id
group by usr.id;

--3 Подсчёт для каждого дилера кол-ва машин старше 2018 года с красным кузовом
--В качестве года выпуска применим дату создания записи кузова. не, ну а чо?
select dealer.name,
       (select count(1)
        from m_cars c
                 join m_body b on c.id = b.car_id
        where c.dealer_id = dealer.id
          and b.color = 'RED'
          and c.production_date < to_date('01.01.2018', 'DD.MM.YYYY')
       )
from m_auto_dealer dealer;

--4. Пользователи кроме Российских и Белорусских, с машинами 2010-2015 гв,
--из Германии и купленные не-в-Германии, с двигателем более 3 литров
select distinct usr.surname
    /* car.model,
     car_loc.location,
     usr_loc.location,
     engine.volume,
     dealer.name,
     deal_loc.location*/
from m_users usr
         --inner. потому Безмашинные, без локейшена и тд нас не интересуют
         join m_cars car on usr.id = car.user_id
         join m_auto_dealer dealer on car.dealer_id = dealer.id
         join l_car_location l_car_loc on l_car_loc.car_id = car.id
         join m_location car_loc on l_car_loc.location_id = car_loc.id
         join l_user_location l_usr_loc on l_usr_loc.user_id = usr.id
         join m_location usr_loc on l_usr_loc.location_id = usr_loc.id
         join l_dealer_location l_deal_loc on l_deal_loc.dealer_id = car.dealer_id
         join m_location deal_loc on l_deal_loc.location_id = deal_loc.id
         join m_engine engine on car.id = engine.car_id
where car_loc.location not like ('Germany')
  and usr_loc.location not in ('Russia', 'Belarus')
  and deal_loc.location not in('Germany')
  and engine.volume > 3
  and car.production_date >= to_date('01.01.2010', 'DD.MM.YYYY')
  and car.production_date < to_date('01.01.2016', 'DD.MM.YYYY');
;

--5 Логины пользователей, у которых более 3 машин
select usr.login
from m_users usr
         join m_cars car on usr.id = car.user_id
group by usr.id
having count(car.id) > 3;

--6 Уникальные дилеры и суммарная стоимость проданных дилером машин
select dealer.name, sum(car.price)
from m_auto_dealer dealer
         inner join m_cars car on dealer.id = car.dealer_id
group by dealer.id;

--7 Кол-во уникальных ползователей-владельцев хотя бы 1 машины вышесредней стоимости
select count(distinct usr.id)
from m_users usr
         join m_cars car on usr.id = car.user_id
where car.price > (select avg(price) from m_cars);