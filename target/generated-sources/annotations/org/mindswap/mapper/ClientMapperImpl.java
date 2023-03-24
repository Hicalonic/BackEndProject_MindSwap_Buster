package org.mindswap.mapper;

import javax.annotation.processing.Generated;

import org.mindswap.model.Role;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-24T10:36:59+0000",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client fromDtoToEntity(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.firstName( clientDto.getFirstName() );
        client.lastName( clientDto.getLastName() );
        client.email( clientDto.getEmail() );

        return client.build();
    }

    @Override
    public ClientDto fromEntityToDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDto = ClientDto.builder();

        clientDto.firstName( client.getFirstName() );
        clientDto.lastName( client.getLastName() );
        clientDto.email( client.getEmail() );

        return clientDto.build();
    }

    @Override
    public Client fromCreateDtoToEntity(ClientCreateDto clientCreateDto) {
        if ( clientCreateDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.firstName( clientCreateDto.getFirstName() );
        client.lastName( clientCreateDto.getLastName() );
        client.email( clientCreateDto.getEmail() );
        client.password( clientCreateDto.getPassword() );
        if ( clientCreateDto.getRole() != null ) {
            client.role( Enum.valueOf( Role.class, clientCreateDto.getRole() ) );
        }

        return client.build();
    }

    @Override
    public ClientCreateDto fromEntityToCreateDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientCreateDto.ClientCreateDtoBuilder clientCreateDto = ClientCreateDto.builder();

        clientCreateDto.firstName( client.getFirstName() );
        clientCreateDto.lastName( client.getLastName() );
        clientCreateDto.email( client.getEmail() );
        clientCreateDto.password( client.getPassword() );
        if ( client.getRole() != null ) {
            clientCreateDto.role( client.getRole().name() );
        }

        return clientCreateDto.build();
    }

    @Override
    public Client fromUpdateDtoToEntity(ClientUpdateDto clientUpdateDto) {
        if ( clientUpdateDto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.firstName( clientUpdateDto.getFirstName() );
        client.lastName( clientUpdateDto.getLastName() );
        client.email( clientUpdateDto.getEmail() );
        client.password( clientUpdateDto.getPassword() );

        return client.build();
    }

    @Override
    public ClientUpdateDto fromEntityToUpdateDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientUpdateDto.ClientUpdateDtoBuilder clientUpdateDto = ClientUpdateDto.builder();

        clientUpdateDto.firstName( client.getFirstName() );
        clientUpdateDto.lastName( client.getLastName() );
        clientUpdateDto.email( client.getEmail() );
        clientUpdateDto.password( client.getPassword() );

        return clientUpdateDto.build();
    }
}
