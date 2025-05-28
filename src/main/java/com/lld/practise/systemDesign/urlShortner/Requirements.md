URL SHORTNER:

Functional Requirements:

1. Given a URL, our service should generate a shorter and unique alias of it. This
   is called a short link.
2. When users access a short link, our service should redirect them to the
   original link.
3. Users should optionally be able to pick a custom short link for their URL.
4. Links will expire after a standard default timespan. Users should be able to
   specify the expiration time.


Non-Functional Requirements:

1. The system should be highly available. This is required because, if our service
   is down, all the URL redirections will start failing.
2. URL redirection should happen in real-time with minimal latency.
3. Shortened links should not be guessable (not predictable).


Estimation
What we need to find
1. MAX QPS: Redirect: 5*10^4 QPS
2. Storage over a year
3. Cache Storage
4. Bandwith


QPS Calculation
1.Url generated over a year MILLION :10^6
2. Url generated over a month: 10^6/10: 10^5
3. Url generated over a day: 10^5/30=3*10^3=3000 url
   **Url generaterd per sec=3000/60=50 url/sec**

Redirect: Creation Ratio: 1000:1
Total Redirecct per sec: 5*10^4 QPS


Storage:
1. Let each row i our table: short_url, long_url, metadata, expiry_time
2. Each row takes 1kb of storage (Asumme)
3. Total Storage in 1 year: 10^6 * 1KB=10^6 KB=1GB
4. Total storage over 10 years: 10 GB


Cache Storage:
80: 20 rules i.e 20% percent data is most frequenet should be served by our Cache
Let say ttl of a 1 day:
Request per day: QPS* 3600=10^7*10=10^8=100 Million request per day
Total Space: 100 Million *1KB=100 GB
20% cache: 20Gb this would be the limit if every request redirection is unqiue that wont be the case

Bandwith (Throughput)
1. Write Operation:Url generated per sec*data=50Kb/s
2. Read operation: 5*10^4*1KB=10 Mb/s
   For read requests, since every second we expect ~20K URLs redirections, total
   outgoing data for our service would be 10MB per second:


API DESIGN
1. GET api/v1/redirect?url=<SHORTEN_URL>
   REDIRECT TO ORIGINAL URL
   IF DOESN'T EXIST REDIRECT TO ERROR PAGE OR SHOW 404


2. POST api/v1/create_url
   body: url,expiry_time,creationTimestamp,userid


DB DESIGN model
i will choose sql db structured
Url_Table
1. shorten_url PK
2. long_url
3. userId FK
4. creationTimestamp
5. expiryTimestamp


   Algo for URL Shortening
   HASHING
   Hashing using algo like SHA256 get first 8 letter
   Problem:
   Hash Collission (First 8 letters)
   Flow
   Post requests /api/v1/shorten_url
   hash=generateHash(url).substr(8)
   If it doesnt not exist insert P.K
   Else create another

AUTO INCREMENT  ID DB and convert to BASE 64

New value=GET THE SIZE OF DB+1
Convert it into base 64
Store it
Pros:
64 power 8> total no of url for multiple years for sure
Concurrency issues WE need to synchronise above method
High load on Database because read will also be happening from there (Redirecting)

TICKETING SERVICE based on increment only
LOAD keys in Another DB:
Id, numeric base 64
State: UNUSED,USED
Run a cron that load set of base 64 url in db
Request new url-> select * from db where state=”UNUSED” limit 1-> mark that used
Above will solve high load issues
To solve concurrency problems: synchonization but it will bring latency
Another way:
Load a set of UNUSED keys in redis : mark them loaded in DB :

New Flow:
Request-> backend-> REdis (get key from redis)->mark used in DB
Refile REDIS with new set of keys after like total keys<CERTAIN NO

BOTTLENECKS:

MULTIPLE BACKEND SERVERS
We can use CACHE to store recent redirecting url
Another cache for maintaining set of unused id(new urls)
Refilling Jobs for DB as well as Redis
In future to optimised we can removed used keys in our DB

https://docs.google.com/document/d/1yWFtu3lyGwusKTePyWBgUV-6FxWx-uhxpNDmhkFOlMQ/edit?tab=t.uat52oric2k3
