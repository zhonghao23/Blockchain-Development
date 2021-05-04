# Blockchain-Development
Blockchain Implementation for Supply Chain Management with Concepts like Block, Hashing, Cryptography and Digital Signatures in Java

# Motivation
The blockchain development is one of the elective subjects I've taken in university. The idea of implementing blockchain
into supply chain management is to enable easy tracking of supplies and save time in communication between people involved in the supply chain.

# Tech/Framework Used
1. Block Concepts
2. SHA256 Hashing
3. Cryptography (Secret Key, Public Key, Hash Function)
4. Digital Signatures

# Files (Purpose)
1. AStoPCcontract.txt - Encrypted Agriculture Supplier's digital signed contract for Producer
2. PCtoRTcontract.txt - Encrypted Producer's digital signed contract for Retailer
3. login.txt - Login Details (username, UUID, entity)
4. secret.txt - Contains UUID, Initial Sale Value used for hashing, Initially Hashed Password with Salt
5. SupplyChain-Ledger.txt - The Hashed Blockchain Represented in .txt format

# Screenshots
## Sample UI
![image](https://user-images.githubusercontent.com/63278063/116956101-00fd4600-acc7-11eb-9ade-91bfc7b0e7e7.png)

![image](https://user-images.githubusercontent.com/63278063/116956127-0f4b6200-acc7-11eb-911f-95f96fec9452.png)

![image](https://user-images.githubusercontent.com/63278063/116956162-20946e80-acc7-11eb-8cdb-41569ecbc63b.png)
* AgroSupplier could create contracts


![image](https://user-images.githubusercontent.com/63278063/116956186-32761180-acc7-11eb-9be9-e4e16879cfbf.png)
* Processor could view, reject or accept the contract offered by agro supplier and create contracts.


![image](https://user-images.githubusercontent.com/63278063/116956278-76691680-acc7-11eb-9bc2-171f45674f1b.png)
* Retailer could view, reject or accept the contract offered by producer.
