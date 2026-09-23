import { Injectable, NotFoundException } from '@nestjs/common';

@Injectable()
export class UsersService {
  private users = [
    {
      id: 1,
      username: 'dung',
      role: 'student',
    },
    {
      id: 2,
      username: 'teacher',
      role: 'teacher',
    },
  ];

  getAllUsers() {
    return this.users;
  }

  getUserById(id: number) {
    const user = this.users.find((user) => user.id === id);

    if (!user) {
      throw new NotFoundException('User not found');
    }

    return user;
  }

  createUser(username: string, role: string) {
    const newUser = {
      id: this.users.length + 1,
      username,
      role,
    };

    this.users.push(newUser);

    return newUser;
  }

  updateUser(id: number, username: string, role: string) {
    const user = this.users.find((user) => user.id === id);

    if (!user) {
      throw new NotFoundException('User not found');
    }

    user.username = username;
    user.role = role;

    return user;
  }

  deleteUser(id: number) {
    const index = this.users.findIndex((user) => user.id === id);

    if (index === -1) {
      throw new NotFoundException('User not found');
    }

    this.users.splice(index, 1);

    return {
      message: 'Deleted successfully',
    };
  }
}