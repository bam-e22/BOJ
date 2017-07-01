import Foundation

while true {
	
	let N = Int(readLine()!)!
	
	if N == 0 {
		
		break
	}

	var S : [String] = []

	for	i in 0..<N {
	
		S.append(readLine()!)
	}

	S = S.sorted(by: {$0.capitalized < $1.capitalized})
	
	print(S.first!)
}
