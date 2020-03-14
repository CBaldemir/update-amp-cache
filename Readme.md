# AMP CACHE UPDATE

  - You can use this system if you still have old AMP content after changing the AMP content. With this system, you can update the cache of your AMP pages.


### How to use it?
  1-) Take the project to your computer.
 ```sh  
 git clone https://github.com/CBaldemir/update-amp-cache.git
 ```
 2-) If you have private key, move to this directory.
  ```sh  
 cd update-amp-cache/src/main/resources
 ```
 3-) If you haven't private key, create a private pem file (RSA) this directory.
  ```sh  
 openssl genrsa 2048 > private-key.pem
 ```
 4-) Java programming is use Pkcs package for private key  signature.
 ```sh  
 openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in private-key.pem -out pkcs8-key.pem
```
5-) Then come back in the update-amp-cache directory and run project.
 ```sh  
 mvn spring-boot:run
```
6-) Make post request.
> cacheApiDomain: Amp Cache Domain Suffix 
> domain: Your domain name
 ```sh  
 curl -d '{"cacheApiDomain":"value1", "domain":"value2"}' -H "Content-Type: application/json" -X POST http://localhost:8080/update-cache
```
> For more detail:
```sh 
https://developers.google.com/amp/cache/update-cache
```