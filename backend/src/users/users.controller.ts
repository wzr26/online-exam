import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Post,
  Put,
} from '@nestjs/common';

import { UsersService } from './users.service';

@Controller('api/users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  getAllUsers() {
    return this.usersService.getAllUsers();
  }

  @Get(':id')
  getUserById(@Param('id') id: string) {
    return this.usersService.getUserById(Number(id));
  }

  @Post()
  createUser(
    @Body() body: { username: string; role: string },
  ) {
    return this.usersService.createUser(
      body.username,
      body.role,
    );
  }

  @Put(':id')
  updateUser(
    @Param('id') id: string,
    @Body() body: { username: string; role: string },
  ) {
    return this.usersService.updateUser(
      Number(id),
      body.username,
      body.role,
    );
  }

  @Delete(':id')
  deleteUser(@Param('id') id: string) {
    return this.usersService.deleteUser(Number(id));
  }
}