package com.gymsoft.domain.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gymsoft.domain.dto.CustomerDTO;
import com.gymsoft.domain.entity.Customer;
import com.gymsoft.domain.repository.CustomerRepository;

@Service( "customerService" )
public class CustomerService
{

    private CustomerRepository customerRepository;

    CustomerService( CustomerRepository customerRepository )
    {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer( CustomerDTO customerDto )
    {

        Objects.requireNonNull( customerDto );

        String customerEmail = customerDto.getEmail();

        if( null != customerEmail )
        {
            Optional<Customer> customerFoundByEmail = customerRepository.findByEmail( customerEmail );

            if( customerFoundByEmail.isPresent() )
            {
                throw new RuntimeException( "Customer already exist with this email" );
            }
        }

        Optional<Customer> customerFoundByPin = customerRepository.findByPin( customerDto.getPin() );

        if( customerFoundByPin.isPresent() )
        {
            throw new RuntimeException( "Customer already exist with this PIN" );
        }

        Customer customer = new Customer();
        customer.setName( customerDto.getName() );
        customer.setEmail( customerDto.getEmail() );
        customer.setMobile( customerDto.getMobile() );
        customer.setDateOfJoin( customerDto.getDateOfJoin() );
        customer.setLastDate( customerDto.getLastDate() );
        customer.setGender( customerDto.getGender() );
        customer.setPin( customerDto.getPin() );
        customer.setKycType( customerDto.getKycType() );
        customer.setDocNumber( customerDto.getDocNumber() );
        String avatarName = customerDto.getName() + "_" + customerDto.getPin() + ".jpg";
        String docImageName =
            customerDto.getKycType() + "_" + customerDto.getName() + "_" + customerDto.getPin() + ".jpg";

        if( storeImage( customerDto.getAvatar(), avatarName ) )
        {
            customer.setAvatar( avatarName );
        }
        if( storeImage( customerDto.getDocImage(), docImageName ) )
        {
            customer.setDocImage( docImageName );
        }
        ;

        customerRepository.save( customer );

        return customer;

    }

    private boolean storeImage( MultipartFile imageFile, String name )
    {
        try
        {
            if( imageFile != null )
            {
                byte[] fileBytes = imageFile.getBytes();
                Path path = Paths.get( "/photos/" + name );
                Files.write( path, fileBytes );
                return true;
            }
            return false;
        }
        catch( IOException e )
        {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Customer> getCustomer( String email )
    {
        return customerRepository.findByEmail( email );
    }

    public Optional<Customer> getCustomerByPin( String pin )
    {
        return customerRepository.findByPin( pin );
    }

    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll()
        		.stream()
        		.filter( c-> !c.isDeleted() )
        		.collect( Collectors.toList() );
    }

    public Integer getTotalCount()
    {
        return customerRepository.findAll()
        		.stream()
        		.filter( c-> !c.isDeleted() )
        		.collect( Collectors.toList() )
        		.size();
    }

    public List<Customer> getActiveCustomers()
    {
        List<Customer> customers = customerRepository.findAll();
        Iterator<Customer> itr = customers.iterator();
        while( itr.hasNext() )
        {
            Customer c = itr.next();
            if( !c.isActive() || c.isDeleted() )
            {
                itr.remove();
            }
        }
        return customers;
    }

    public Optional<Customer> getCustomer( Long id )
    {
        return customerRepository.findById( id );
    }

    public void updateCustomer( CustomerDTO customerDto )
    {
        Objects.requireNonNull( customerDto );

        Long id = customerDto.getId();

        Optional<Customer> customerFound = customerRepository.findById( id );
        if( customerFound.isPresent() )
        {
            Customer customer = customerFound.get();
            customer.setName( customerDto.getName() );
            customer.setEmail( customerDto.getEmail() );
            customer.setMobile( customerDto.getMobile() );
            customer.setDateOfJoin( customerDto.getDateOfJoin() );
            customer.setLastDate( customerDto.getLastDate() );
            customer.setGender( customerDto.getGender() );
            customer.setPin( customerDto.getPin() );
            customer.setKycType( customerDto.getKycType() );
            customer.setDocNumber( customerDto.getDocNumber() );
            customer.setDeleted( customerDto.isDeleted() );
            String avatarName = customerDto.getName() + "_" + customerDto.getPin() + ".jpg";
            String docImageName =
                customerDto.getKycType() + "_" + customerDto.getName() + "_" + customerDto.getPin() + ".jpg";

            if( storeImage( customerDto.getAvatar(), avatarName ) )
            {
                customer.setAvatar( avatarName );
            }
            if( storeImage( customerDto.getDocImage(), docImageName ) )
            {
                customer.setDocImage( docImageName );
            }
            ;

            customerRepository.save( customer );
        }

    }

    public List<Customer> getDueCustomers()
    {
        List<Customer> customers = customerRepository.findAll();
        Iterator<Customer> itr = customers.iterator();
        while( itr.hasNext() )
        {
            Customer c = itr.next();
            if( c.isActive() || c.isDeleted() )
            {
                itr.remove();
            }
        }
        return customers;
    }

    public List<Customer> searchByNameContains( String searchKey )
    {
        return customerRepository.findByNameContainingIgnoreCase( searchKey )
        		.stream()
        		.filter( c-> !c.isDeleted() )
        		.collect( Collectors.toList() );
    }
    
    public void save( Customer customer )
    {
        customerRepository.save( customer );
    }

}
