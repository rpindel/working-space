# import oracledb

# def wm_connection(efc):
#     oracledb.init_oracle_client("C:\\Users\\tk78876\\Downloads\\instantclient_21_10")

#     username="wsteam"
#     userpwd = "wsteam"
#     host = "OR1132PNL.KOHLS.COM"
#     port = 1521
#     service_name = "OP1132_RPT_STB1.kohls.com"

#     dsn = f'{username}/{userpwd}@{host}:{port}/{service_name}'
#     connection = oracledb.connect(dsn)
    
#     # query = "select max(created_dttm) from wmos_efc.orders"
    
#     query = """with hours AS (select hr,
#         cast(mod(rownum +17,24) as number) as hold
#            from
#               (SELECT '21' as hr from dual
#               union
#               SELECT '22' as hr from dual
#               union
#               SELECT '23' as hr from dual
#               union
#               SELECT '00' as hr from dual
#               union
#               SELECT '01' as hr from dual
#               union
#               SELECT '02' as hr from dual
#               union
#               SELECT '03' as hr from dual
#               union
#               SELECT '04' as hr from dual
#               union
#               SELECT '05' as hr from dual
#               union
#               SELECT '06' as hr from dual
#               union
#               SELECT '07' as hr from dual
#               union
#               SELECT '08' as hr from dual
#               union
#               SELECT '09' as hr from dual
#               union
#               SELECT '10' as hr from dual
#               union
#               SELECT '11' as hr from dual
#               union
#               SELECT '12' as hr from dual
#               union
#               SELECT '13' as hr from dual
#               union
#               SELECT '14' as hr from dual
#               union
#               SELECT '15' as hr from dual
#               union
#               SELECT '16' as hr from dual
#               union
#               SELECT '17' as hr from dual
#               union
#               SELECT '18' as hr from dual
#               union
#               SELECT '19' as hr from dual
#               union
#               SELECT '20' as hr from dual
#               union
#               SELECT '21' as hr from dual) hours              order by mod(rownum +18 ,24))
# select to_char(sysdate - (case when to_number(to_char(sysdate,'HH24')) between 0 and 4 then 2 else 1 end),'mm/dd/yyyy') as Day,
# max(case when hours.hr = to_char(sysdate,'HH24') then hours.hold end) over () as current_hour,
# (case when to_number(to_char(sysdate,'HH24')) between 0 and 4 then 1 else 0 end) as Days_Back,
# to_number(HOURS.HR) as HRS,
# HOURS.HR, 
# '873' as WHSE,
# to_number(HOURS.HOLD) HOLD,
# nvl(MANIFEST.Manifest,0) Manifest
# from hours
# left outer join

# (select manifest.hr, sum(manifest.cage_manifest) cage_manifest, sum(manifest.ns_manifest) ns_manifest, sum(manifest.sort_manifest) sort_manifest,
#  sum(manifest.put_manifest) put_manifest, sum(manifest.singles_manifest) singles_manifest,
# sum(manifest.sa_manifest) sa_manifest, sum(manifest.manifest) MANIFEST
# from
# (select to_char(MANIFEST.CREATED_DTTM+ 1/24, 'hh24') as hr,
# sum(case when (MANIFEST.misc_instr_code_1 = 'CG'
# and MANIFEST.ord_type <> 'SA') then MANIFEST.nbr_units else 0 end) as cage_manifest,
# sum(case when (MANIFEST.misc_instr_code_1 in ('NC','NS', 'XX', NULL)
# and MANIFEST.wave_type not like 'PUT%' and MANIFEST.ord_type <> 'SA') then MANIFEST.nbr_units else 0 end) as ns_manifest,
# sum(case when (MANIFEST.misc_instr_code_1 IN ('OC', 'ST')
# and MANIFEST.nbr_units > 1 and MANIFEST.wave_type not like 'PUT%') then MANIFEST.nbr_units else 0 end) as sort_manifest,
# sum(case when (MANIFEST.wave_type like 'PUT%' and MANIFEST.ord_type <> 'SA') then MANIFEST.nbr_units else 0 end) as put_manifest,
# sum(case when (MANIFEST.misc_instr_code_1 IN ('ST', 'OC') and MANIFEST.nbr_units= 1
# and MANIFEST.wave_type not like 'PUT%') then MANIFEST.nbr_units else 0 end) as singles_manifest,
# sum(case when (MANIFEST.ORD_TYPE =  'SA') then MANIFEST.nbr_units else 0 end) as sa_manifest,
# SUM(MANIFEST.nbr_units) as Manifest
# from wmos_efc.PNA_UNITS_TRKG MANIFEST
# where (MANIFEST.CREATED_DTTM) between trunc(sysdate - (case when to_number(to_char(sysdate,'HH24')) between 0 and 4 then 1 else 0 end))  + 5/24 and sysdate
#     group by to_char(MANIFEST.CREATED_DTTM+ 1/24, 'hh24')     

# union

# select  to_char(PTT.begin_date + 1/24, 'hh24') as hr,
# 0 as cage_manifest, 0 as ns_manifest, 0 as sort_manifest, 0 as put_manifes, 0 as singles_manifest, 0 as sa_manifest,
# sum(ptt.nbr_units) Manifest
# from prod_trkg_tran ptt, lpn l, orders o, ship_wave_parm swp
# where o.order_id = l.order_id
# and l.wave_nbr = swp.ship_wave_nbr
# and ptt.cntr_nbr = l.tc_lpn_id
# and PTT.begin_date between trunc(sysdate - (case when to_number(to_char(sysdate,'HH24')) between 0 and 4 then 1 else 0 end))  + 5/24 and sysdate
# and ptt.menu_optn_name = 'Load Trailer'
# group by to_char(PTT.begin_date + 1/24, 'hh24')) MANIFEST
# group by manifest.hr) MANIFEST
# on (hours.hr = MANIFEST.hr)

# ORDER by HOURS.HOLD"""

#     cursor = connection.cursor()
#     cursor.execute(query)
#     for result in cursor.execute(query):
#         print(result)    
    
#     connection.close()
    

# wm_connection("eFC1")


currentHour = 23
nextHour = 0
if currentHour in (range(0, 23)):
        nextHour = currentHour + 1
else:
    currentHour == 23
    
print(currentHour)
print(nextHour)