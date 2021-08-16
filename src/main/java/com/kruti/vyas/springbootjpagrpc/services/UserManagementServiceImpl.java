/*
 * **********************************************************************************************
 *
 *  Copyright 2021  Kruti Vyas
 *  Use of this source code is governed by Apache 2.0 license that can be found in the LICENSE file or at
 *  https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * *********************************************************************************************
 */

package com.kruti.vyas.springbootjpagrpc.services;

import com.google.protobuf.Timestamp;
import com.kruti.vyas.springbootjpagrpc.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import com.kruti.vyas.springbootjpagrpc.model.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Kruti Vyas
 */
@GrpcService
public class UserManagementServiceImpl extends UserManagementServiceGrpc.UserManagementServiceImplBase {

    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest req, StreamObserver<CreateUserReply> responseObserver) {
        UserEntity user = UserEntity.builder()
                .name(req.getName())
                .email(req.getEmail())
                .build();
        user=userRepository.save(user);

        CreateUserReply reply = CreateUserReply.newBuilder()
                .setUser(com.kruti.vyas.springbootjpagrpc.services.User.newBuilder()
                        .setID(user.getId())
                        .setName(user.getName())
                        .setEmail(user.getEmail())
                        .setCreatedAt(Timestamp.newBuilder()
                                .setNanos(user.getCreatedAt().getNanos())
                                .build())
                        .build()
                )
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserReply> responseObserver) {
        Optional<UserEntity> user = userRepository.findById(request.getId());
        if(user.isPresent())
        {
            UserEntity u = user.get();
            GetUserReply reply = GetUserReply.newBuilder()
                    .setUser(User.newBuilder()
                            .setID(u.getId())
                            .setName(u.getName())
                            .setEmail(u.getEmail())
                            .setCreatedAt(Timestamp.newBuilder()
                                    .setNanos(u.getCreatedAt().getNanos())
                                    .build())
                            .build())
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void updateUser(UpdateUserRequest req, StreamObserver<UpdateUserReply> responseObserver) {
        //to be implemented
    }

    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteUserReply> responseObserver) {
        //to be implemented
    }

    @Override
    public void listUsers(ListUsersRequest request, StreamObserver<ListUsersReply> responseObserver) {
        List<UserEntity> users = userRepository.findAll();
        ListUsersReply.Builder replyBuilder = ListUsersReply.newBuilder();
        int count = 0;
        users.stream().forEach(
                x-> {
                    User user = User.newBuilder()
                        .setID(x.getId())
                        .setName(x.getName())
                        .setEmail(x.getEmail())
                        .setCreatedAt(Timestamp.newBuilder()
                                .setNanos(x.getCreatedAt().getNanos())
                                .build())
                        .build();
                    replyBuilder.addUsers(count,user);
                });
        responseObserver.onNext(replyBuilder.build());
        responseObserver.onCompleted();
    }
}