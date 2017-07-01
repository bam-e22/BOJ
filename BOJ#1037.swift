import Foundation

let count : Int = Int(readLine()!)!
var arr = readLine()!.characters.split(separator: " ").map{ Int(String($0))! }

arr = arr.sorted(by: { $0 < $1 })

if count < 2 {
	
	print(arr[0] * arr[0])
} else {
	
	print(arr[count-1] * arr[0])
}
