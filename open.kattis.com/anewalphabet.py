mapped_values = dict()
mapped_values["a"] = "@"
mapped_values["b"] = "8"
mapped_values["c"] = "("
mapped_values["d"] = "|)"
mapped_values["e"] = "3"
mapped_values["f"] = "#"
mapped_values["g"] = "6"
mapped_values["h"] = "[-]"
mapped_values["i"] = "|"
mapped_values["j"] = "_|"
mapped_values["k"] = "|<"
mapped_values["l"] = "1"
mapped_values["m"] = "[]\/[]"
mapped_values["n"] = "[]\[]"
mapped_values["o"] = "0"
mapped_values["p"] = "|D"
mapped_values["q"] = "(,)"
mapped_values["r"] = "|Z"
mapped_values["s"] = "$"
mapped_values["t"] = "']['"
mapped_values["u"] = "|_|"
mapped_values["v"] = "\/"
mapped_values["w"] = "\/\/"
mapped_values["x"] = "}{"
mapped_values["y"] = "`/"
mapped_values["z"] = "2"

str = input()
output_str = ""
for i in range(0, len(str)):
    ch = str[i].lower()
    if ch in mapped_values:
        output_str += mapped_values[ch]
    else:
        output_str += ch
print(output_str)




