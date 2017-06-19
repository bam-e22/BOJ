import Foundation

let N : Int = Int(readLine()!)!
var arr : [Int] = []


for i in 0..<N {
	
	arr.append(Int(readLine()!)!)
}

//arr = arr.sorted()
arr = arr.sorted(by: { $0 < $1 })

for x in arr {
	
	print(x)
}


