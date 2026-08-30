def evens(list_of_ints):
    """Returns a copy of the list but keeping only the even numbers."""        
    return_list = []
    for i in list_of_ints:
        if i % 2 == 0:
            return_list.append(i)
    return return_list

def count_words(list_of_words):
    """Returns a map from each word to its count."""
    counts = {}
    for word in list_of_words:
        if word in counts:
            counts[word] = counts[word]+1
        else:
            counts[word] = 1
    return counts

class Dog:
    def __init__(self, name, size) -> None:
        self.name = name
        self.size = size

    def grow(self):
        self.size += 1

    def __str__(self):
        return f"{self.name} the size {self.size} dog"
