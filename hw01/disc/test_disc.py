import unittest

from disc import Dog, count_words, evens


class DiscTest(unittest.TestCase):
    def test_evens(self):
        values = [0, 1, -2, -3, 8, 11]
        self.assertEqual([0, -2, 8], evens(values))
        self.assertEqual([], evens([]))
        self.assertEqual(values, [0, 1, -2, -3, 8, 11])

    def test_count_words(self):
        words = ["dog", "cat", "dog", "bird", "cat", "dog"]
        self.assertEqual({"dog": 3, "cat": 2, "bird": 1}, count_words(words))
        self.assertEqual({}, count_words([]))

    def test_dog_grow_and_string(self):
        dog = Dog("Mochi", 3)
        dog.grow()
        self.assertEqual(4, dog.size)
        self.assertEqual("Mochi the size 4 dog", str(dog))


if __name__ == "__main__":
    unittest.main()
