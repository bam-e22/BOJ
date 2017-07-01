import Foundation

let inputArr = readLine()!.characters.split(separator: " ").map({Int(String($0))!})

var num : Int = 0

for x in inputArr {
    
    num += x*x
}

print(num % 10)
