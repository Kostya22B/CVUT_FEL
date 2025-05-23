import sys
from typing import Optional, List, Tuple, Dict

BASE = 32

def custom_hash(s: str, m: int) -> int:
    sum = 0
    for i, value in enumerate(s):
        if ord(value) != 32:
            code = ord(value) - ord('a') + 1
        else:
            code = 31
        sum += code * (BASE ** i)
    return sum % m


class HashTable:
    def __init__(self, m: int = 11) -> None:
        self.quantity: int = 0
        self.default_size: int = m
        self.table: Dict[int, Optional[Tuple[str, int]]] = {i: None for i in range(m)}

    def extend(self, size: int) -> None:
        old_table = self.table
        self.table = {i: None for i in range(size)}
        self.quantity = 0

        for index, node in old_table.items():
            if node is None or node[0] is None:
                continue

            index = self.insert(node[0])
            self.table[index] = (node[0], node[1])

    def insert(self, value: str) -> int:
        calculated_hash = custom_hash(value, len(self.table))

        next_value = self.table[calculated_hash]
        next_index = calculated_hash
        count = len(self.table)
        inserted_index = -1

        while count > 0:
            if next_value is None:
                self.table[next_index] = (value, 1)
                self.quantity += 1
                inserted_index = next_index
                break

            if next_value[0] is None:
                index = self.search(value)


                if index == -1:
                    self.table[next_index] = (value, 1)
                    self.quantity += 1
                    inserted_index = next_index
                    break
                elif index != -1:
                    node = self.table[index]
                    self.table[index] = (node[0], node[1] + 1)
                    inserted_index = index
                    break

            if next_value[0] == value:
                self.table[next_index] = (next_value[0], next_value[1] + 1)
                inserted_index = next_index
                break

            next_index = (next_index + 1) % len(self.table)
            next_value = self.table[next_index]
            count -= 1

        if self.quantity / len(self.table) >= 0.7:
            self.extend(len(self.table) * 2)
            return self.search(value)

        return inserted_index

    def search(self, value: str) -> int:
        calculated_hash = custom_hash(value, len(self.table))

        next_value = self.table[calculated_hash]
        next_index = calculated_hash
        count = len(self.table)

        while count > 0:
            if next_value is None:
                return -1

            if next_value[0] == value:
                return next_index

            next_index = (next_index + 1) % len(self.table)
            next_value = self.table[next_index]
            count -= 1

        return -1

    def delete(self, value: str) -> Optional[int]:
        index = self.search(value)

        if index == -1:
            return -1

        node = self.table[index]
        self.table[index] = (node[0], node[1] - 1)

        if self.table[index][1] == 0:
            self.table[index] = (None, 0)
            self.quantity -= 1

        if self.quantity / len(self.table) <= 0.3:
            new_size = len(self.table) // 2
            if new_size >= self.default_size:
                self.extend(new_size)

            return self.search(value)

        return index

class Executor:
    def __init__(self) -> None:
        self.current_command: Optional[str] = None
        self.current_person: Optional[int] = None
        self.was_initialized: bool = False
        self.hash_tables: Dict[int, HashTable] = {}
        self.names: Dict[int, str] = {
            1: "Mirek",
            2: "Jarka",
            3: "Jindra",
            4: "Rychlonozka",
            5: "Cervenacek"
        }
        self.command_mapping = {
            'a': self.__command_add,
            'p': self.__command_print,
            'd': self.__command_delete,
        }

    def __init_tables(self) -> None:
        if self.was_initialized:
            return

        self.hash_tables = {i: HashTable() for i in range(1, 6)}
        self.was_initialized = True

    def __command_initialize(self, array: List[str]) -> None:
        if self.was_initialized:
            return

        for i, size in enumerate(array, start=1):
            if i not in self.hash_tables:
                self.hash_tables[i] = HashTable(int(size))

        for j in range(len(array) + 1, 6):
            if j not in self.hash_tables:
                self.hash_tables[j] = HashTable()
        self.was_initialized = True

    def __command_choose(self, index: int) -> None:
        if not (1 <= index <= 5):
            sys.stderr.write("Error: Chybny vstup!\n")
            return

        self.current_command = 'choose'
        self.current_person = index

    def __command_add(self) -> None:
        self.current_command = 'a'
        self.current_person = None

    def __command_print(self) -> None:
        if self.current_person is None:
            sys.stderr.write("Error: Chybny vstup!\n")
            return

        self.current_command = 'p'

        sys.stdout.write(self.names[self.current_person] + "\n")
        sys.stdout.write("\t"
                         + str(len(self.hash_tables[self.current_person].table))
                         + " "
                         + str(self.hash_tables[self.current_person].quantity)
                         + "\n")

    def __command_delete(self) -> None:
        if self.current_person is None:
            sys.stderr.write("Error: Chybny vstup!\n")
            return

        self.current_command = 'd'

    def process_command(self, line: str) -> None:
        if line[0] == '#':
            args = line.split(' ')

            if len(args[0]) != 2:
                sys.stderr.write("Error: Chybny vstup!\n")
                return

            command = line[1]
            if command.isdigit() and int(command) in range(1, 6):
                self.__command_choose(int(command))
            elif command in ['a', 'p', 'd']:
                getattr(self, f'__command_{command}')()
            else:
                sys.stderr.write("Error: Chybny vstup!\n")
        else:
            self.process_command(line)

    # for test
    def count_unique_messages(self) -> int:
        unique_messages = set()
        current_hashtable = self.hash_tables[self.current_person]

        for node in current_hashtable.table.values():
            if node is not None and node[0] is not None:
                unique_messages.add(node[0])

        return len(unique_messages)

    def go(self) -> None:
        while True:
            try:
                line = input().strip()

                if line == '':
                    break
                if not self.was_initialized:
                    if line.startswith('#i'):
                        args = line.split(' ')
                        if len(args) < 2:
                            sys.stderr.write("Error: Chybny vstup!\n")
                            continue
                        self.__command_initialize(args[1::])
                        continue
                    else:
                        self.__init_tables()

                if line[0] == '#':
                    args = line.split(' ')

                    if len(args[0]) != 2:
                        sys.stderr.write("Error: Chybny vstup!\n")
                        continue

                    command = line[1]
                    if command in self.command_mapping:
                        self.command_mapping[command]()
                        continue
                    elif command.isdigit():
                        self.__command_choose(int(command))
                        continue
                    else:
                        sys.stderr.write("Error: Chybny vstup!\n")
                        continue

                if self.current_command == 'a':
                    for i in self.hash_tables:
                        self.hash_tables[i].insert(line)
                elif self.current_command == 'p':
                    current_hashtable = self.hash_tables[self.current_person]
                    index = current_hashtable.search(line)

                    if index == -1:
                        sys.stdout.write("\t" + line + " " + "-1 0" + "\n")
                    else:
                        sys.stdout.write("\t" + line + " "
                                         + str(index) + " "
                                         + str(current_hashtable.table[index][1]) + "\n")
                elif self.current_command == 'd':
                    self.hash_tables[self.current_person].delete(line)

            except EOFError:
                break


command_executor = Executor()
command_executor.go()
